package me.him188.ani.datasources.api.topic

import kotlinx.serialization.Serializable
import java.nio.file.Paths


@Serializable
sealed class ResourceLocation {
    abstract val uri: String

    /**
     * `magnet:?xt=urn:btih:...`
     */
    @Serializable
    data class MagnetLink(override val uri: String) : ResourceLocation() {
        init {
            require(uri.startsWith("magnet:")) {
                "MagnetLink uri must start with magnet:"
            }
        }
    }

    /**
     * `*.torrent` form `http://`, `https://`.
     */
    @Serializable
    data class HttpTorrentFile(override val uri: String) : ResourceLocation() {
        init {
            require(uri.startsWith("https://") || uri.startsWith("http://")) {
                "HttpTorrentFile uri must start with http:// or https://"
            }
        }
    }

    /**
     * `*.mkv`, `*.mp4` form `http://`, `https://`.
     */
    @Serializable
    data class HttpStreamingFile(override val uri: String) : ResourceLocation() {
        init {
            require(uri.startsWith("https://") || uri.startsWith("http://")) {
                "HttpStreamingFile uri must start with http:// or https://"
            }
        }
    }

    /**
     * `file://`
     */
    @Serializable
    data class LocalFile(
        val filePath: String, // absolute
    ) : ResourceLocation() {
        override val uri: String by lazy {
            Paths.get(filePath).toUri().toString()
        }
    }
}
