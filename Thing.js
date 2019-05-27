/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, { Component } from "react";
import { Image } from "react-native";

type Props = {
  index: number,
  width: number
};

export default class Thing extends Component<Props> {
  render() {
    return (
      <Image
        source={{
          uri: "https://media0.giphy.com/media/FVIkcKlhpGoSI/giphy.gif"
        }}
        style={{ width: this.props.width }}
      />
    );
  }
}
