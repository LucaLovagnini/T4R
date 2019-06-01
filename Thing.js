/**
 * @flow
 */

import React from "react";
import FadeInView from "./FadeInView";
import CenteredText from "./CenteredText";
import { AppState, StyleSheet, Image, View, Dimensions } from "react-native";
import Sound from "react-native-sound";

type Props = {
  isDisplayed: boolean,
  text: string,
  backgroundColor: ?string,
  image: ?number,
  sound: ?string
};

type State = {
  soundPlayer: Sound
};

const { width } = Dimensions.get("window");

export default class Thing extends React.PureComponent<Props, State> {
  state = {
    soundPlayer: null
  };

  static defaultProps = {
    isDisplayed: false,
    text: "This is a thing",
    backgroundColor: null,
    image: null,
    sound: null
  };

  static getDerivedStateFromProps(nextProps: Props, prevState: State) {
    if (
      nextProps.sound &&
      nextProps.isDisplayed &&
      prevState.soundPlayer === null
    ) {
      var soundPlayer = new Sound(nextProps.sound, Sound.MAIN_BUNDLE, error => {
        if (error) {
          // eslint-disable-next-line no-console
          console.log("failed to load the sound", error);
          return;
        }
        soundPlayer.play();
        soundPlayer.setNumberOfLoops(-1);
      });
      return {
        soundPlayer: soundPlayer
      };
    } else if (!nextProps.isDisplayed && prevState.soundPlayer !== null) {
      prevState.soundPlayer.stop();
      prevState.soundPlayer.release();
      return { soundPlayer: null };
    }
    return null;
  }

  _handleAppStateChange = nextAppState => {
    if (this.state.soundPlayer !== null) {
      if (nextAppState !== "active") {
        this.state.soundPlayer.stop();
      } else {
        this.state.soundPlayer.play();
      }
    }
  };

  componentWillUnmount() {
    AppState.removeEventListener("change", this._handleAppStateChange);
  }

  render() {
    // If not displayed, render a black card.
    // Do not return null! Otherwise, only the displayed card will be rendered...
    // ... but it will be placed as the first card (since its the only existing one!)
    if (!this.props.isDisplayed) {
      AppState.removeEventListener("change", this._handleAppStateChange);
      return <View style={{ ...styles.fullScreen, background: "black" }} />;
    }

    AppState.addEventListener("change", this._handleAppStateChange);
    const background = this.props.image ? (
      <Image
        source={this.props.image}
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
