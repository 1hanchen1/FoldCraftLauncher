package com.mio.manager

import android.content.Context
import com.mio.data.Renderer
import com.tungsten.fcl.R
import com.tungsten.fclauncher.plugins.DriverPlugin
import com.tungsten.fclauncher.plugins.RendererPlugin
import com.tungsten.fclauncher.utils.FCLPath

object RendererManager {
    lateinit var RENDERER_MOBILEGLUES: Renderer
    private var isInit = false

    @JvmStatic
    val rendererList: MutableList<Renderer> = mutableListOf()
        get() {
            if (!isInit) {
                init(FCLPath.CONTEXT)
            }
            return field
        }

    fun init(context: Context) {
        isInit = true
        rendererList.clear()
        RENDERER_MOBILEGLUES = Renderer(
            "MobileGlues",
            "MobileGlues『内置的』",
            "libmobileglues.so",
            "libEGL.so",
            "",
            null,
            null,
            Renderer.ID_MOBILEGLUES,
            "1.17",
            ""
        )
        RendererPlugin.init(context)
        addRenderer()
        DriverPlugin.init(context)
    }

    private fun addRenderer() {
        rendererList.add(RENDERER_MOBILEGLUES)
        rendererList.addAll(RendererPlugin.rendererList)
    }

    fun refresh(context: Context) {
        RendererPlugin.refresh(context)
        rendererList.clear()
        addRenderer()
    }

    @JvmStatic
    fun getRenderer(id: String): Renderer {
        return rendererList.find { it.id == id } ?: RENDERER_MOBILEGLUES
    }

    @JvmStatic
    fun getRendererOrNull(id: String): Renderer? {
        return rendererList.find { it.id == id }
    }
}