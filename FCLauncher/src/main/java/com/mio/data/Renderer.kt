package com.mio.data

data class Renderer(
    val name: String,
    val des: String,
    val glName: String,
    val eglName: String,
    val path: String,
    val boatEnv: List<String>?,
    val pojavEnv: List<String>?,
    val id: String,
    val minMCver: String = "",
    val maxMCver: String = ""
) {
    companion object {
        const val ID_MOBILEGLUES = "0000495a-5503-eaf5-9e3d-1ba086260000"
    }

    fun getGLPath(): String {
        if (path.isEmpty()) return glName
        return "$path/$glName"
    }

    fun isEqual(id: String): Boolean {
        return this.id == id
    }
}
