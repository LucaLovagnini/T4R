/**
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
  backgroundColor: ?string,
  image: ?string,
  sound: ?string
};

type State = {
  loopListener: ?Function
};

const { width } = Dimensions.get("window");

export default class Thing extends React.PureComponent<Props, State> {
  state = {
    loopListener: null
  };

  static defaultProps = {
    isDisplayed: false,
    text: "This is a thing",
    backgroundColor: null,
    image: null,
    sound: null
  };

  static getDerivedStateFromProps(nextProps: Props, prevState: *) {
    if (
      nextProps.sound &&
      nextProps.isDisplayed &&
      prevState.loopListener === null
    )
      return {
        loopListener: SoundPlayer.addEventListener(
          "FinishedPlaying",
          ({ success }) => {
            if (success && nextProps.sound)
              SoundPlayer.playSoundFile(nextProps.sound, "mp3");
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
      return <View style={{ ...styles.fullScreen, background: "black" }} />;

    if (this.props.isDisplayed && sound) {
      try {
        SoundPlayer.playSoundFile(sound, "mp3");
      } catch (e) {
        // eslint-disable-next-line no-console
        console.log(`Cannot play the sound file ` + sound, e);
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

const styles = StyleSheet.create({
  fullScreen: {
    width: width,
    height: "100%"
  }
});
