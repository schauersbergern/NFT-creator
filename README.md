# Create NFTs and Transfer tokens

This is an Android app where you can upload every resource from your smartphone and turn it into an NFT  and you easily can deploy your own tokens and transfer them

**Warning: This project is currently using Rinkeby testnet and NOT Ethereum mainnet, so you are actually save from wasting real Ether**

### Technology used:

**Architecture:**

I am strongly dedicated to use **clean architecture** to separate UI, Domain, Datasource and Network logic. I am using the MVI state management approach where the UI observes viewmodel state for updating UI. User interaction triggers events on the viewmodel which also yields UI changes.

**UI:**
For the UI I use the single Activity multiple Fragment approach where UI logic is only implemented in Fragments (android sdk imports only in Fragments or Activities)

**Domain Logic:**
Domain logic (like smart contract interaction via network or persisting or fetching data) is implemented in UseCases. For connecting Domain and UI I am going with the Model View Intent (MVI) approach and I am using orbit mvi (https://github.com/orbit-mvi/orbit-mvi) for state management.

**Dependency Injection:**
In this project I am using Koin (https://github.com/InsertKoinIO/koin) to inject dependencies into viewmodels, usecases, etc. because in my opinion it uses least boilerplate code besides of Dagger and Hilt

**Other Libraries:**

- web3j for smart contract communication:
  https://github.com/web3j/web3j
- kotlinx-serialization for serialising data objects to json
- Java ipfs client for uploading NFT files to IPFS (inter planetary
  file system) https://github.com/ipfs-shipyard/java-ipfs-http-client
- QR Code Scanner https://github.com/yuriy-budiyev/code-scanner
- Vanilla fragment transactions for navigation (no jetpack navigation or much better FragNav https://github.com/ncapdevi/FragNav this time)

### Getting started:

1. Check out the project
2. Deploy the NFT and Token solidity contracts from the contracts package to rinkby testnet as described here: https://medium.com/interfacing-with-a-blockchain/communicating-with-an-ethereum-smart-contract-via-android-24ee0dd2c115
3. In config.kt update MYTOKEN_CONTRACT_ADDRESS and NFTOKEN_CONTRACT_ADDRESS to your NFT and Token contract addresses.
4. Add a file named secret.properties in your root folder
5. Add the private key like `PRIVATE_KEY = c3aa087557782766a298252c90248d0657a50349c7c3aea2643884ffbaa4070c` from the addresses you deployed the contracts. This is needed to load the contracts.
6. Compile the app and install it to your phone

### Usage:

**Scan the address which will own the NFTs**

When you first open the app a QR Code scanner will open where you have to scan your ethereum address e.g. from metamask

![Scan 1](/screenshots/scan1.jpg) ![Scan 2](/screenshots/scan2.jpg)

**Mint your NFT**

In the "Create NFT" tab you can first upload any resource (currently supported: jpg, png, jpeg, pdf, mp4, mp3, m4a) from your phones file system to ipfs. (You can add support for new filetypes by extending SUPPORTED_FILE_TYPES in config.kt)

When you are satisfied what you see in preview you can mint your NFT which will write the ipfs url to the rinkeby ethereum blockchain.

![Create 1](/screenshots/create1.jpg) ![Create 2](/screenshots/create2.jpg)  ![Create 3](/screenshots/create3.jpg)

**Admire your minted NFTs**

In the "My NFTs" tab you can admire your NFTs in a list

![Admire 1](/screenshots/admire1.jpg)

**Transfer Token to your address**

In the "Tokens" tab you can see
- The total amount of "MyToken" with Symbol "MTK" supplied
- The amount of MTK Token the token creator holds
- The amount of MTK Token the current user holds

With tapping the "Transfer 5 Token" button you can transfer 5 MTK Token to your current address

![Transfer 1](/screenshots/transfer1.jpg) ![Transfer 2](/screenshots/transfer2.jpg)

*Props to https://stackedit.io/app where I created this README*