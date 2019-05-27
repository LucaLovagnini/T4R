import React from "react";
import { Animated, Dimensions } from "react-native";
import PropTypes from "prop-types";

type Props = {
  children: PropTypes.node.isRequired,
  backgroundColor: string
};

const { width } = Dimensions.get("window");

export default class FadeInView extends React.PureComponent<Props> {
  state = {
    fadeAnim: new Animated.Value(0) // Initial value for opacity: 0
  };

  componentDidMount() {
    Animated.timing(
      // Animate over time
      this.state.fadeAnim, // The animated value to drive
      {
        toValue: 1, // Animate to opacity: 1 (opaque)
        duration: 2000 // Make it take a while
      }
    ).start(); // Starts the animation
  }

  render() {
    let { fadeAnim } = this.state;
    let backgroundColor =
      this.props.backgroundColor !== "undefined"
        ? this.props.backgroundColor
        : "white";

    return (
      <Animated.View // Special animatable View
        style={{
          justifyContent: "center",
          width: width,
          height: "100%",
          backgroundColor: backgroundColor,
          opacity: fadeAnim // Bind opacity to animated value
        }}
      >
        {this.props.children}
      </Animated.View>
    );
  }
}
