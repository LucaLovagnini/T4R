/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React from "react";
import FadeInView from "./FadeInView";
import CenteredText from "./CenteredText";
import SoundPlayer from "react-native-sound-player";
import { StyleSheet, Image, View, Dimensions } from "react-native";

type Props = {
  isDisplayed: boolean,
  text: string,
  backgroundColor?: string,
  image?: string,
  sound?: string
};

const { width } = Dimensions.get("window");

export default class Thing extends React.PureComponent<Props> {
  state = {
    loopListener: null
  };

  static getDerivedStateFromProps(nextProps, prevState) {
    if (
      nextProps.isDisplayed &&
      nextProps.sound &&
      prevState.loopListener === null
    )
      return {
        loopListener: SoundPlayer.addEventListener(
          "FinishedPlaying",
          ({ success }) => {
            if (success) SoundPlayer.playSoundFile(nextProps.sound, "mp3");
          }
        )
      };
    else if (!nextProps.isDisplayed && prevState.loopListener !== null) {
      prevState.loopListener.remove();
      return { loopListener: null };
    }
    return null;
  }

  render() {
    const { sound } = this.props;

    // If not displayed, render a black card.
    // Do not return null! Otherwise, only the displayed card will be rendered...
    // ... but it will be placed as the first card (since its the only existing one!)
    if (!this.props.isDisplayed)
      return <View style={[styles.fullScreen, { background: "black" }]} />;

    if (this.props.isDisplayed && sound) {
      try {
        SoundPlayer.playSoundFile(sound, "mp3");
      } catch (e) {
        // eslint-disable-next-line no-console
        console.log(`cannot play the sound file`, e);
      }
    }

    const background = this.props.image ? (
      <Image
        source={{
          uri: this.props.image
        }}
        style={[styles.fullScreen, { opacity: 0.5 }]}
      />
    ) : (
      <View
        style={[
          styles.fullScreen,
          {
            backgroundColor: this.props.backgroundColor
              ? this.props.backgroundColor
              : "blue"
          }
        ]}
      />
    );

    return (
      <FadeInView>
        {background}
        <CenteredText>{this.props.text}</CenteredText>
      </FadeInView>
    );
  }
}

Thing.defaultProps = {
  isDisplayed: false,
  text: "This is a thing",
  backgroundColor: null,
  image: null,
  sound: null
};

const styles = StyleSheet.create({
  fullScreen: {
    width: width,
    height: "100%"
  }
});
