package at.mintech.nftmaker.data

import io.ipfs.api.IPFS
import io.ipfs.api.MerkleNode
import io.ipfs.api.NamedStreamable
import io.ipfs.multiaddr.MultiAddress
import java.io.IOException

class IpfsManager( private val address: MultiAddress ) {

    private lateinit var ipfsClient: IPFS

    init {
        Thread { ipfsClient = IPFS(address) }.start()
    }

    fun addFile(name : String, data: ByteArray) : String {
        val file = NamedStreamable.ByteArrayWrapper(name, data)
        try {
            val addResult: MerkleNode = ipfsClient.add(file).get(0)
            return addResult.hash.toBase58()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return ""

    }

}