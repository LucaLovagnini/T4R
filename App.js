/**
 * @flow
 */

import React from "react";
import Thing from "./Thing";
import AsyncStorage from "@react-native-community/async-storage";
import { timeoutPromise } from "./timeoutPromise";
import { AppState } from "react-native";
import SoundPlayer from "react-native-sound-player";
import KeepAwake from "react-native-keep-awake";
import { Images } from "./images";

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
    AppState.addEventListener("change", this._handleAppStateChange);
    this._retrieveData();
  }

  componentWillUnmount() {
    AppState.removeEventListener("change", this._handleAppStateChange);
  }

  _handleAppStateChange = nextAppState => {
    if (nextAppState !== "active") {
      SoundPlayer.stop();
    } else {
      this.forceUpdate();
    }
  };

  render() {
    const data = [
      {
        key: "1",
        text: "Life is Strange",
        image: Images.chloeandmaxnew,
        sound: "maxandchloe"
      },
      { key: "2", text: "Aida", sound: "aida", backgroundColor: "blue" },
      {
        key: "3",
        text: "Fantasia",
        image: Images.fantasia,
        sound: "fantasiasong"
      },
      { key: "4", text: "Cartoni Morti", image: Images.cartonimorti },
      { key: "5", text: "Il Piccolo Principe", image: Images.piccoloprincipe },
      { key: "6", text: "Surf Camp", image: Images.tent, sound: "rain" },
      {
        key: "7",
        text: "Casa Patas",
        sound: "flamenco",
        backgroundColor: "red"
      },
      { key: "8", text: "Partner", image: Images.partner },
      {
        key: "9",
        text: "Cloud Atlas",
        image: Images.cloudatlas,
        sound: "endtitle"
      },
      { key: "10", text: "Tre Colli", backgroundColor: "green" },
      {
        key: "11",
        text: "The Wolf",
        sound: "thewolf",
        backgroundColor: "darkgray"
      },
      {
        key: "12",
        text: "Queen\nLive Aid",
        image: Images.wembley,
        sound: "wembleysound"
      },
      {
        key: "13",
        text: "Memola",
        sound: "forest",
        backgroundColor: "darkolivegreen"
      },
      { key: "14", text: "Totoro", image: Images.totoro, sound: "totorosong" },
      { key: "15", text: "Silence", backgroundColor: "black" },
      {
        key: "16",
        text: "Road to Bilbao",
        sound: "newslang",
        backgroundColor: "cornflowerblue"
      },
      { key: "17", text: "Volterra", image: Images.volterra },
      { key: "18", text: "#girodeilGiappone", image: Images.girodeilgiappone },
      { key: "19", text: "Vivere la Vita", sound: "viverelavita" },
      { key: "20", text: "Bojack", image: Images.bojack },
      { key: "21", text: "Grotta Byron", sound: "wavesandseagull" },
      { key: "22", text: "Spirited Away", sound: "spiritedaway" },
      { key: "23", text: "Happy!", image: Images.happy },
      { key: "24", text: "Peñalara", image: Images.penalara },
      { key: "25", text: "Bimbi Sperduti", image: Images.bimbisperduti },
      {
        key: "26",
        text: "La Stanza al Quarto Piano in Calle de la Cabeza",
        backgroundColor: "indigo"
      },
      {
        key: "27",
        text: "La La Land",
        image: Images.lalaland,
        sound: "lalalandpiano"
      },
      { key: "28", text: "Cerro del\nTio Pio", image: Images.cerrodeltiopio }
    ];

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
            data={data}
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
