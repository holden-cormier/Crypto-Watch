# Crytpo-Watch

## Description:
Our final project is called CryptoWatch and it is an app where users can view a list of crypto currencies, along with their names, prices and symbols. Inaddition to this the user can click on a crypto currency and see a more detailed view on the crypto. On this view the price, the daily percent change, market cap, the highest price, the lowest price, the supply and the volume. 

## Technologies used:
- AndroidSDK
- CoinGecko HTTP API

## Documentation:

`MainActivity.java`
This contains the main recycler view logic that lists the fetched cryptocurrencies as well as the `FetchCryptoData` class that handles collecting the crypto data from CoinGecko and parsing it into a readable format for the program

`CryptoWatchAdapter.java`
The adapter in charge of populating each list item in the recylcer view and handling the onclick to redirect to the crypto detail activity

`CryptoItem.java`
Encapsualtes data for a cryptocurrency coin

`CryptoWatchDetailActivity.java`
Displays a detailed view of the crypto with more stats
