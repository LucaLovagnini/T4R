/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, { Component } from "react";
import { View, FlatList, Dimensions } from "react-native";
import ImageLoader from "./ImageLoader";

type Props = {};

export default class App extends Component<Props> {
  constructor(props) {
    super(props);
  }

  render() {
    const width = Math.round(Dimensions.get("window").width);
    const height = Math.round(Dimensions.get("window").height);
    const handleScroll = e => {
      // eslint-disable-next-line no-console
      console.log(
        (Math.round(e.nativeEvent.contentOffset.x) % width) + " " + width
      );
    };
    return (
      <FlatList
        getItemLayout={(data, index) => ({
          length: width,
          offset: width * index,
          index
        })}
        showsHorizontalScrollIndicator={false}
        pagingEnabled={true}
        horizontal={true}
        onScroll={handleScroll}
        style={{ flex: 1 }}
        data={[
          { key: "1" },
          { key: "2" },
          { key: "3" },
          { key: "4" },
          { key: "5" },
          { key: "6" },
          { key: "7" },
          { key: "8" },
          { key: "9" },
          { key: "10" },
          { key: "11" },
          { key: "12" },
          { key: "13" },
          { key: "14" },
          { key: "15" },
          { key: "16" },
          { key: "17" },
          { key: "18" }
        ]}
        renderItem={({ item }) => (
          <View
            style={{
              width: width,
              alignItems: "center",
              justifyContent: "center"
            }}
          >
            <ImageLoader
              source={{
                uri: "https://media0.giphy.com/media/FVIkcKlhpGoSI/giphy.gif"
              }}
              style={{ width: width, height: height }}
              isShown={true}
            />
          </View>
        )}
      />
    );
  }
}
