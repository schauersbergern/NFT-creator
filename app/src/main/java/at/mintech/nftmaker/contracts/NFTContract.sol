pragma solidity ^0.8.0;

import "https://github.com/nibbstack/erc721/src/contracts/tokens/nf-token-metadata.sol";
import "https://github.com/nibbstack/erc721/src/contracts/ownership/ownable.sol";

contract NFTContract is
NFTokenMetadata,
Ownable
{
    constructor()
    {
        nftName = "Niks Legendary Medals";
        nftSymbol = "LEG";
    }

    /**
     * @dev Mints a new NFT.
   * @param _to The address that will own the minted NFT.
   * @param _tokenId of the NFT to be minted by the msg.sender.
   * @param _uri String representing RFC 3986 URI.
   */
    function mint( address _to, uint256 _tokenId, string calldata _uri ) external onlyOwner {
        super._mint(_to, _tokenId);
        super._setTokenUri(_tokenId, _uri);
    }
}