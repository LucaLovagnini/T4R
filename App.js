/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, { Component } from "react";
import FadeInView from "./FadeInView";
import CenteredText from "./CenteredText";
import AsyncStorage from "@react-native-community/async-storage";
import { timeoutPromise } from "./timeoutPromise";

import { FlatList, Dimensions, Image, StyleSheet, View } from "react-native";

const { width } = Dimensions.get("window");
const INDEX_STORAGE = "index";

export default class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      initialScrollIndex: null
    };
    this.viewabilityConfig = {
      waitForInteraction: true,
      viewAreaCoveragePercentThreshold: 99
    };
    this.onViewableItemsChanged = this.onViewableItemsChanged.bind(this);
  }

  _storeIndex = async index => {
    try {
      await AsyncStorage.setItem(INDEX_STORAGE, String(index));
      // eslint-disable-next-line no-console
      console.log("Storing: " + index);
    } catch (error) {
      // eslint-disable-next-line no-console
      console.log("Error while sotring state: " + error);
    }
  };

  _retrieveData = async () => {
    //Wait at most 5 seconds from storage, otherwise start over.
    const promise = timeoutPromise(5000, AsyncStorage.getItem(INDEX_STORAGE));

    promise.then(value => {
      // eslint-disable-next-line no-console
      console.log("Retrieved: " + value);
      if (value !== null && value !== "[object Undefined]") {
        this.setState({
          initialScrollIndex: value
        });
      }
    });

    promise.catch(error => {
      // eslint-disable-next-line no-console
      console.log("Error while sotring state: " + error);
      this.setState({
        initialScrollIndex: 0
      });
    });
  };

  onViewableItemsChanged({ viewableItems }) {
    viewableItems.forEach(item => {
      const { isViewable, index } = item;
      if (isViewable) this._storeIndex(index);
    });
  }

  componentDidMount() {
    this._retrieveData();
  }

  render() {
    if (this.state.initialScrollIndex === null) {
      return null;
    } else {
      return (
        <View style={{ flex: 1, backgroundColor: "black" }}>
          <FlatList
            getItemLayout={(data, index) => ({
              length: width,
              offset: width * index,
              index
            })}
            onViewableItemsChanged={this.onViewableItemsChanged}
            viewabilityConfig={this.viewabilityConfig}
            showsHorizontalScrollIndicator={false}
            initialScrollIndex={this.state.initialScrollIndex}
            pagingEnabled={true}
            horizontal={true}
            style={styles.flatList}
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
              <FadeInView>
                <Image
                  source={{
                    uri:
                      "https://media0.giphy.com/media/FVIkcKlhpGoSI/giphy.gif"
                  }}
                  style={styles.image}
                />
                <CenteredText>{item.key}</CenteredText>
              </FadeInView>
            )}
          />
        </View>
      );
    }
  }
}

const styles = StyleSheet.create({
  text: { textAlign: "center" },
  flatList: { flex: 1 },
  image: {
    width: "100%",
    height: "100%",
    opacity: 0.5
  }
});
