import React from "react";
import { Animated } from "react-native";

type Props = {
  isShown: boolean
};

export default class ImageLoader extends React.Component<Props> {
  state = {
    opacity: new Animated.Value(0),
    isShown: this.props.isShown
  };

  onLoad = () => {
    Animated.timing(this.state.opacity, {
      toValue: 1,
      duration: 2000,
      useNativeDriver: true
    }).start();
  };

  render() {
    return this.state.isShown ? (
      <Animated.Image
        onLoad={this.onLoad}
        {...this.props}
        style={[
          {
            opacity: this.state.opacity
          },
          this.props.style
        ]}
      />
    ) : null;
  }
}
