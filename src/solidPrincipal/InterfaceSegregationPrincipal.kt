package solidPrincipal

//Break interfaces into smaller parts and only let class implement the necessary interfaces.

/**
violation Example:

interface MediaPlayer{
    fun playMusic()
    fun playVideo()
    fun showImage()
}

class VideoPlayer : MediaPlayer {
    override fun playMusic() {
        println("Playing Audio.")
    }

    override fun playVideo() {
        println("Play video")
    }

    override fun showImage() {
        throw UnsupportedOperationException("Cant show Images.")
    }

}

class ImagePlayer : MediaPlayer {
    override fun playMusic() {
        throw UnsupportedOperationException("Cant play just Music")
    }

    override fun playVideo() {
        throw UnsupportedOperationException("Cant play Video")
    }

    override fun showImage() {
        println("Showing Images.")
    }

}

class AudioPlayer : MediaPlayer {
    override fun playMusic() {
        println("Playing Audio.")
    }

    override fun showImage() {
        throw UnsupportedOperationException("Cant play just Music")
    }

    override fun playVideo() {
        throw UnsupportedOperationException("Cant play Video")
    }
}
*/

interface CanPlayVideo {
    fun playVideo()
}
interface CanPlayMusic {
    fun playMusic()
}
interface CanShowImage{
    fun showImage()
}

class VideoPlayer : CanPlayVideo, CanPlayMusic {
    override fun playVideo() {
        println("Play video")
    }

    override fun playMusic() {
        println("Playing Music.")
    }
}

class ImagePlayer : CanShowImage {

    override fun showImage() {
        println("Showing Images.")
    }

}

class AudioPlayer : CanPlayMusic {
    override fun playMusic() {
        println("Playing Music.")
    }
}


