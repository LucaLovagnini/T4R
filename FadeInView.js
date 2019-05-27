import React from "react";
import { Animated, Dimensions } from "react-native";
import PropTypes from "prop-types";

type Props = {
  children: PropTypes.node.isRequired,
};

const { width } = Dimensions.get("window");

export default class FadeInView extends React.Component<Props> {
  state = {
    fadeAnim: new Animated.Value(0),  // Initial value for opacity: 0
  }

  componentDidMount() {
    Animated.timing(                  // Animate over time
      this.state.fadeAnim,            // The animated value to drive
      {
        toValue: 1,                   // Animate to opacity: 1 (opaque)
        duration: 5000,              // Make it take a while
      }
    ).start();                        // Starts the animation
  }

  render() {
    let { fadeAnim } = this.state;

    return (
      <Animated.View                 // Special animatable View
        style={{
          justifyContent: "center",
          width: width,
          height: "100%",
          opacity: fadeAnim,         // Bind opacity to animated value
        }}
      >
        {this.props.children}
      </Animated.View>
    );
  }
}
