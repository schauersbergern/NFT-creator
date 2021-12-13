package at.mintech.nftmaker.contracts;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class NFTContract extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b5060016000806301ffc9a760e01b7bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060006101000a81548160ff02191690831515021790555060016000806380ac58cd60e01b7bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060006101000a81548160ff0219169083151502179055506001600080635b5e139f60e01b7bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060006101000a81548160ff02191690831515021790555033600860006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506040518060400160405280601581526020017f4e696b73204c6567656e64617279204d6564616c7300000000000000000000008152506005908051906020019062000193929190620001e8565b506040518060400160405280600381526020017f4c4547000000000000000000000000000000000000000000000000000000000081525060069080519060200190620001e1929190620001e8565b50620002fd565b828054620001f69062000298565b90600052602060002090601f0160209004810192826200021a576000855562000266565b82601f106200023557805160ff191683800117855562000266565b8280016001018555821562000266579182015b828111156200026557825182559160200191906001019062000248565b5b50905062000275919062000279565b5090565b5b80821115620002945760008160009055506001016200027a565b5090565b60006002820490506001821680620002b157607f821691505b60208210811415620002c857620002c7620002ce565b5b50919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b612d06806200030d6000396000f3fe608060405234801561001057600080fd5b50600436106101165760003560e01c80638da5cb5b116100a2578063c87b56dd11610071578063c87b56dd146102df578063d3fc98641461030f578063e985e9c51461032b578063f2fde38b1461035b578063f3fe3bc31461037757610116565b80638da5cb5b1461026b57806395d89b4114610289578063a22cb465146102a7578063b88d4fde146102c357610116565b806323b872dd116100e957806323b872dd146101b557806342842e0e146101d15780636352211e146101ed57806370a082311461021d578063860d248a1461024d57610116565b806301ffc9a71461011b57806306fdde031461014b578063081812fc14610169578063095ea7b314610199575b600080fd5b61013560048036038101906101309190612891565b610395565b6040516101429190612a12565b60405180910390f35b6101536103fc565b6040516101609190612a2d565b60405180910390f35b610183600480360381019061017e91906128e3565b61048e565b60405161019091906129ab565b60405180910390f35b6101b360048036038101906101ae91906127e9565b6105a9565b005b6101cf60048036038101906101ca91906126de565b61098c565b005b6101eb60048036038101906101e691906126de565b610dde565b005b610207600480360381019061020291906128e3565b610dfe565b60405161021491906129ab565b60405180910390f35b61023760048036038101906102329190612679565b610ee4565b6040516102449190612a4f565b60405180910390f35b610255610f9e565b6040516102629190612a2d565b60405180910390f35b610273610fd7565b60405161028091906129ab565b60405180910390f35b610291610ffd565b60405161029e9190612a2d565b60405180910390f35b6102c160048036038101906102bc91906127ad565b61108f565b005b6102dd60048036038101906102d8919061272d565b61118c565b005b6102f960048036038101906102f491906128e3565b6111e3565b6040516103069190612a2d565b60405180910390f35b61032960048036038101906103249190612825565b6112d3565b005b610345600480360381019061034091906126a2565b6113fa565b6040516103529190612a12565b60405180910390f35b61037560048036038101906103709190612679565b61148e565b005b61037f6116c0565b60405161038c9190612a2d565b60405180910390f35b6000806000837bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060009054906101000a900460ff169050919050565b60606005805461040b90612bd3565b80601f016020809104026020016040519081016040528092919081815260200182805461043790612bd3565b80156104845780601f1061045957610100808354040283529160200191610484565b820191906000526020600020905b81548152906001019060200180831161046757829003601f168201915b5050505050905090565b600081600073ffffffffffffffffffffffffffffffffffffffff166001600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156040518060400160405280600681526020017f30303330303200000000000000000000000000000000000000000000000000008152509061056c576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016105639190612a2d565b60405180910390fd5b506002600084815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16915050919050565b8060006001600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690503373ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614806106a25750600460008273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff165b6040518060400160405280600681526020017f303033303033000000000000000000000000000000000000000000000000000081525090610719576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016107109190612a2d565b60405180910390fd5b5082600073ffffffffffffffffffffffffffffffffffffffff166001600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156040518060400160405280600681526020017f3030333030320000000000000000000000000000000000000000000000000000815250906107f6576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016107ed9190612a2d565b60405180910390fd5b5060006001600086815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690508073ffffffffffffffffffffffffffffffffffffffff168673ffffffffffffffffffffffffffffffffffffffff1614156040518060400160405280600681526020017f3030333030380000000000000000000000000000000000000000000000000000815250906108d6576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016108cd9190612a2d565b60405180910390fd5b50856002600087815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550848673ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b92560405160405180910390a4505050505050565b8060006001600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690503373ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161480610a5d57503373ffffffffffffffffffffffffffffffffffffffff166002600084815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16145b80610aee5750600460008273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff165b6040518060400160405280600681526020017f303033303034000000000000000000000000000000000000000000000000000081525090610b65576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610b5c9190612a2d565b60405180910390fd5b5082600073ffffffffffffffffffffffffffffffffffffffff166001600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156040518060400160405280600681526020017f303033303032000000000000000000000000000000000000000000000000000081525090610c42576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610c399190612a2d565b60405180910390fd5b5060006001600086815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690508673ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16146040518060400160405280600681526020017f303033303037000000000000000000000000000000000000000000000000000081525090610d21576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610d189190612a2d565b60405180910390fd5b50600073ffffffffffffffffffffffffffffffffffffffff168673ffffffffffffffffffffffffffffffffffffffff1614156040518060400160405280600681526020017f303033303031000000000000000000000000000000000000000000000000000081525090610dca576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610dc19190612a2d565b60405180910390fd5b50610dd586866116f9565b50505050505050565b610df9838383604051806020016040528060008152506117ae565b505050565b60006001600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614156040518060400160405280600681526020017f303033303032000000000000000000000000000000000000000000000000000081525090610ede576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610ed59190612a2d565b60405180910390fd5b50919050565b60008073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1614156040518060400160405280600681526020017f303033303031000000000000000000000000000000000000000000000000000081525090610f8d576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610f849190612a2d565b60405180910390fd5b50610f9782611d7c565b9050919050565b6040518060400160405280600681526020017f303138303032000000000000000000000000000000000000000000000000000081525081565b600860009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60606006805461100c90612bd3565b80601f016020809104026020016040519081016040528092919081815260200182805461103890612bd3565b80156110855780601f1061105a57610100808354040283529160200191611085565b820191906000526020600020905b81548152906001019060200180831161106857829003601f168201915b5050505050905090565b80600460003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055508173ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167f17307eab39ab6107e8899845ad3d59bd9653f200f220920489ca2b5937696c31836040516111809190612a12565b60405180910390a35050565b6111dc85858585858080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f820116905080830192505050505050506117ae565b5050505050565b606081600073ffffffffffffffffffffffffffffffffffffffff166001600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156040518060400160405280600681526020017f3030333030320000000000000000000000000000000000000000000000000000815250906112c1576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016112b89190612a2d565b60405180910390fd5b506112cb83611dc5565b915050919050565b600860009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16146040518060400160405280600681526020017f30313830303100000000000000000000000000000000000000000000000000008152509061139b576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016113929190612a2d565b60405180910390fd5b506113a68484611e6a565b6113f48383838080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f82011690508083019250505050505050612058565b50505050565b6000600460008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16905092915050565b600860009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16146040518060400160405280600681526020017f303138303031000000000000000000000000000000000000000000000000000081525090611556576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161154d9190612a2d565b60405180910390fd5b50600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614156040518060400160405280600681526020017f3031383030320000000000000000000000000000000000000000000000000000815250906115ff576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016115f69190612a2d565b60405180910390fd5b508073ffffffffffffffffffffffffffffffffffffffff16600860009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a380600860006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b6040518060400160405280600681526020017f303138303031000000000000000000000000000000000000000000000000000081525081565b60006001600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905061173a82612162565b611744818361219b565b61174e8383612306565b818373ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef60405160405180910390a4505050565b8160006001600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690503373ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16148061187f57503373ffffffffffffffffffffffffffffffffffffffff166002600084815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16145b806119105750600460008273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff165b6040518060400160405280600681526020017f303033303034000000000000000000000000000000000000000000000000000081525090611987576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161197e9190612a2d565b60405180910390fd5b5083600073ffffffffffffffffffffffffffffffffffffffff166001600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156040518060400160405280600681526020017f303033303032000000000000000000000000000000000000000000000000000081525090611a64576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401611a5b9190612a2d565b60405180910390fd5b5060006001600087815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690508773ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16146040518060400160405280600681526020017f303033303037000000000000000000000000000000000000000000000000000081525090611b43576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401611b3a9190612a2d565b60405180910390fd5b50600073ffffffffffffffffffffffffffffffffffffffff168773ffffffffffffffffffffffffffffffffffffffff1614156040518060400160405280600681526020017f303033303031000000000000000000000000000000000000000000000000000081525090611bec576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401611be39190612a2d565b60405180910390fd5b50611bf787876116f9565b611c168773ffffffffffffffffffffffffffffffffffffffff1661248e565b15611d725760008773ffffffffffffffffffffffffffffffffffffffff1663150b7a02338b8a8a6040518563ffffffff1660e01b8152600401611c5c94939291906129c6565b602060405180830381600087803b158015611c7657600080fd5b505af1158015611c8a573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190611cae91906128ba565b905063150b7a0260e01b7bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916817bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916146040518060400160405280600681526020017f303033303035000000000000000000000000000000000000000000000000000081525090611d6f576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401611d669190612a2d565b60405180910390fd5b50505b5050505050505050565b6000600360008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b6060600760008381526020019081526020016000208054611de590612bd3565b80601f0160208091040260200160405190810160405280929190818152602001828054611e1190612bd3565b8015611e5e5780601f10611e3357610100808354040283529160200191611e5e565b820191906000526020600020905b815481529060010190602001808311611e4157829003601f168201915b50505050509050919050565b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1614156040518060400160405280600681526020017f303033303031000000000000000000000000000000000000000000000000000081525090611f12576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401611f099190612a2d565b60405180910390fd5b50600073ffffffffffffffffffffffffffffffffffffffff166001600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16146040518060400160405280600681526020017f303033303036000000000000000000000000000000000000000000000000000081525090611fed576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401611fe49190612a2d565b60405180910390fd5b50611ff88282612306565b808273ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef60405160405180910390a45050565b81600073ffffffffffffffffffffffffffffffffffffffff166001600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156040518060400160405280600681526020017f303033303032000000000000000000000000000000000000000000000000000081525090612134576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161212b9190612a2d565b60405180910390fd5b508160076000858152602001908152602001600020908051906020019061215c9291906124d9565b50505050565b6002600082815260200190815260200160002060006101000a81549073ffffffffffffffffffffffffffffffffffffffff021916905550565b8173ffffffffffffffffffffffffffffffffffffffff166001600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16146040518060400160405280600681526020017f303033303037000000000000000000000000000000000000000000000000000081525090612274576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161226b9190612a2d565b60405180910390fd5b506001600360008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008282546122c59190612af8565b925050819055506001600082815260200190815260200160002060006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690555050565b600073ffffffffffffffffffffffffffffffffffffffff166001600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16146040518060400160405280600681526020017f3030333030360000000000000000000000000000000000000000000000000000815250906123e0576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016123d79190612a2d565b60405180910390fd5b50816001600083815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506001600360008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008282546124839190612aa2565b925050819055505050565b60008060007fc5d2460186f7233c927e7db2dcc703c0e500b653ca82273b7bfad8045d85a47060001b9050833f91506000801b82141580156124d05750808214155b92505050919050565b8280546124e590612bd3565b90600052602060002090601f016020900481019282612507576000855561254e565b82601f1061252057805160ff191683800117855561254e565b8280016001018555821561254e579182015b8281111561254d578251825591602001919060010190612532565b5b50905061255b919061255f565b5090565b5b80821115612578576000816000905550600101612560565b5090565b60008135905061258b81612c74565b92915050565b6000813590506125a081612c8b565b92915050565b6000813590506125b581612ca2565b92915050565b6000815190506125ca81612ca2565b92915050565b60008083601f8401126125e257600080fd5b8235905067ffffffffffffffff8111156125fb57600080fd5b60208301915083600182028301111561261357600080fd5b9250929050565b60008083601f84011261262c57600080fd5b8235905067ffffffffffffffff81111561264557600080fd5b60208301915083600182028301111561265d57600080fd5b9250929050565b60008135905061267381612cb9565b92915050565b60006020828403121561268b57600080fd5b60006126998482850161257c565b91505092915050565b600080604083850312156126b557600080fd5b60006126c38582860161257c565b92505060206126d48582860161257c565b9150509250929050565b6000806000606084860312156126f357600080fd5b60006127018682870161257c565b93505060206127128682870161257c565b925050604061272386828701612664565b9150509250925092565b60008060008060006080868803121561274557600080fd5b60006127538882890161257c565b95505060206127648882890161257c565b945050604061277588828901612664565b935050606086013567ffffffffffffffff81111561279257600080fd5b61279e888289016125d0565b92509250509295509295909350565b600080604083850312156127c057600080fd5b60006127ce8582860161257c565b92505060206127df85828601612591565b9150509250929050565b600080604083850312156127fc57600080fd5b600061280a8582860161257c565b925050602061281b85828601612664565b9150509250929050565b6000806000806060858703121561283b57600080fd5b60006128498782880161257c565b945050602061285a87828801612664565b935050604085013567ffffffffffffffff81111561287757600080fd5b6128838782880161261a565b925092505092959194509250565b6000602082840312156128a357600080fd5b60006128b1848285016125a6565b91505092915050565b6000602082840312156128cc57600080fd5b60006128da848285016125bb565b91505092915050565b6000602082840312156128f557600080fd5b600061290384828501612664565b91505092915050565b61291581612b2c565b82525050565b61292481612b3e565b82525050565b600061293582612a6a565b61293f8185612a80565b935061294f818560208601612ba0565b61295881612c63565b840191505092915050565b600061296e82612a75565b6129788185612a91565b9350612988818560208601612ba0565b61299181612c63565b840191505092915050565b6129a581612b96565b82525050565b60006020820190506129c0600083018461290c565b92915050565b60006080820190506129db600083018761290c565b6129e8602083018661290c565b6129f5604083018561299c565b8181036060830152612a07818461292a565b905095945050505050565b6000602082019050612a27600083018461291b565b92915050565b60006020820190508181036000830152612a478184612963565b905092915050565b6000602082019050612a64600083018461299c565b92915050565b600081519050919050565b600081519050919050565b600082825260208201905092915050565b600082825260208201905092915050565b6000612aad82612b96565b9150612ab883612b96565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff03821115612aed57612aec612c05565b5b828201905092915050565b6000612b0382612b96565b9150612b0e83612b96565b925082821015612b2157612b20612c05565b5b828203905092915050565b6000612b3782612b76565b9050919050565b60008115159050919050565b60007fffffffff0000000000000000000000000000000000000000000000000000000082169050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b60005b83811015612bbe578082015181840152602081019050612ba3565b83811115612bcd576000848401525b50505050565b60006002820490506001821680612beb57607f821691505b60208210811415612bff57612bfe612c34565b5b50919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b6000601f19601f8301169050919050565b612c7d81612b2c565b8114612c8857600080fd5b50565b612c9481612b3e565b8114612c9f57600080fd5b50565b612cab81612b4a565b8114612cb657600080fd5b50565b612cc281612b96565b8114612ccd57600080fd5b5056fea264697066735822122048d8472d344193912ff9fdd56c9056ff088faf65044aab3cd3da5229a44f4e2664736f6c63430008000033";

    public static final String FUNC_CANNOT_TRANSFER_TO_ZERO_ADDRESS = "CANNOT_TRANSFER_TO_ZERO_ADDRESS";

    public static final String FUNC_NOT_CURRENT_OWNER = "NOT_CURRENT_OWNER";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_GETAPPROVED = "getApproved";

    public static final String FUNC_ISAPPROVEDFORALL = "isApprovedForAll";

    public static final String FUNC_MINT = "mint";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_OWNEROF = "ownerOf";

    public static final String FUNC_safeTransferFrom = "safeTransferFrom";

    public static final String FUNC_SETAPPROVALFORALL = "setApprovalForAll";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOKENURI = "tokenURI";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event APPROVALFORALL_EVENT = new Event("ApprovalForAll", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Bool>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    @Deprecated
    protected NFTContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected NFTContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected NFTContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected NFTContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._approved = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse._owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._approved = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse._tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public List<ApprovalForAllEventResponse> getApprovalForAllEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVALFORALL_EVENT, transactionReceipt);
        ArrayList<ApprovalForAllEventResponse> responses = new ArrayList<ApprovalForAllEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._operator = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalForAllEventResponse>() {
            @Override
            public ApprovalForAllEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVALFORALL_EVENT, log);
                ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
                typedResponse.log = log;
                typedResponse._owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._operator = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse._approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVALFORALL_EVENT));
        return approvalForAllEventFlowable(filter);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse._from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse._tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public RemoteFunctionCall<String> CANNOT_TRANSFER_TO_ZERO_ADDRESS() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CANNOT_TRANSFER_TO_ZERO_ADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> NOT_CURRENT_OWNER() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NOT_CURRENT_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String _approved, BigInteger _tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _approved), 
                new org.web3j.abi.datatypes.generated.Uint256(_tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String _owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _owner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getApproved(BigInteger _tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETAPPROVED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> isApprovedForAll(String _owner, String _operator) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISAPPROVEDFORALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _owner), 
                new org.web3j.abi.datatypes.Address(160, _operator)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> mint(String _to, BigInteger _tokenId, String _uri) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MINT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _to), 
                new org.web3j.abi.datatypes.generated.Uint256(_tokenId), 
                new org.web3j.abi.datatypes.Utf8String(_uri)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> ownerOf(BigInteger _tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNEROF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String _from, String _to, BigInteger _tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_safeTransferFrom, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _from), 
                new org.web3j.abi.datatypes.Address(160, _to), 
                new org.web3j.abi.datatypes.generated.Uint256(_tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String _from, String _to, BigInteger _tokenId, byte[] _data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_safeTransferFrom, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _from), 
                new org.web3j.abi.datatypes.Address(160, _to), 
                new org.web3j.abi.datatypes.generated.Uint256(_tokenId), 
                new org.web3j.abi.datatypes.DynamicBytes(_data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setApprovalForAll(String _operator, Boolean _approved) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETAPPROVALFORALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _operator), 
                new org.web3j.abi.datatypes.Bool(_approved)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> supportsInterface(byte[] _interfaceID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SUPPORTSINTERFACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(_interfaceID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> symbol() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> tokenURI(BigInteger _tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOKENURI, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String _from, String _to, BigInteger _tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _from), 
                new org.web3j.abi.datatypes.Address(160, _to), 
                new org.web3j.abi.datatypes.generated.Uint256(_tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String _newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static NFTContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new NFTContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static NFTContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new NFTContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static NFTContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new NFTContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static NFTContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new NFTContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<NFTContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(NFTContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<NFTContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(NFTContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<NFTContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(NFTContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<NFTContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(NFTContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String _owner;

        public String _approved;

        public BigInteger _tokenId;
    }

    public static class ApprovalForAllEventResponse extends BaseEventResponse {
        public String _owner;

        public String _operator;

        public Boolean _approved;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String _from;

        public String _to;

        public BigInteger _tokenId;
    }
}
