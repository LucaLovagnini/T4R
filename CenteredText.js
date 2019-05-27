import React from "react";
import { View, Text, StyleSheet } from "react-native";
import PropTypes from "prop-types";

type Props = {
  children: PropTypes.node.isRequired
};

export default class CenteredText extends React.PureComponent<Props> {
  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.text}>{this.props.children}</Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    position: "absolute",
    top: 0,
    left: 0,
    right: 0,
    bottom: 0,
    justifyContent: "center",
    alignItems: "center"
  },
  text: { color: "white", fontSize: 30, textAlign: "center" },
  flatList: { flex: 1 },
  image: {
    width: "100%",
    height: "100%",
    opacity: 0.5
  }
});
