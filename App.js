/**
 * @flow
 */

import React from "react";
import Thing from "./Thing";
import AsyncStorage from "@react-native-community/async-storage";
import { timeoutPromise } from "./timeoutPromise";
import KeepAwake from "react-native-keep-awake";
import Things from "./Data";

import type { ViewToken } from "ViewabilityHelper";

import { FlatList, Dimensions, StyleSheet, View } from "react-native";

const { width } = Dimensions.get("window");
const INDEX_STORAGE = "index";

type State = { currentIndex: ?number };

export default class App extends React.PureComponent<*, State> {
  constructor(props: any) {
    super(props);

    this.state = {
      currentIndex: null
    };
    this.onViewableItemsChanged = this.onViewableItemsChanged.bind(this);
  }

  _storeIndex = async index => {
    try {
      await AsyncStorage.setItem(INDEX_STORAGE, String(index));
    } catch (error) {
      // eslint-disable-next-line no-console
      console.log("Error while sotring state: " + error);
    }
  };

  _retrieveData = async () => {
    //Wait at most 5 seconds from storage, otherwise start over.
    const promise = timeoutPromise(5000, AsyncStorage.getItem(INDEX_STORAGE));

    promise.then(value => {
      if (value !== null && value !== "[object Undefined]") {
        this.setState({
          currentIndex: Number(value)
        });
      } else {
        this.setState({
          currentIndex: 0
        });
      }
    });

    promise.catch(error => {
      // eslint-disable-next-line no-console
      console.log("Error while sotring state: " + error);
      this.setState({
        currentIndex: 0
      });
    });
  };

  onViewableItemsChanged = ({
    viewableItems
  }: {
    viewableItems: Array<ViewToken>
  }) => {
    viewableItems.forEach(item => {
      const { isViewable, index } = item;
      if (isViewable) {
        this._storeIndex(index);
        this.setState({ currentIndex: index });
      }
    });
  };

  componentDidMount() {
    this._retrieveData();
  }

  render() {
    if (this.state.currentIndex === null) {
      return null;
    } else {
      return (
        <View style={{ flex: 1, backgroundColor: "black" }}>
          <KeepAwake />
          <FlatList
            getItemLayout={(data, index) => ({
              length: width,
              offset: width * index,
              index
            })}
            onViewableItemsChanged={this.onViewableItemsChanged}
            viewabilityConfig={{
              waitForInteraction: true,
              viewAreaCoveragePercentThreshold: 99
            }}
            showsHorizontalScrollIndicator={false}
            initialScrollIndex={this.state.currentIndex}
            pagingEnabled={true}
            horizontal={true}
            style={styles.flatList}
            data={Things}
            renderItem={({ item, index }) => {
              const isDisplayed = this.state.currentIndex === index;
              return <Thing {...item} isDisplayed={isDisplayed} />;
            }}
          />
        </View>
      );
    }
  }
}

const styles = StyleSheet.create({
  flatList: { flex: 1 }
});
