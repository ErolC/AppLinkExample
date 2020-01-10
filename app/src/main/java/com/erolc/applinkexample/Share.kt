package com.erolc.applinkexample

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import java.util.ArrayList

val M_IMAGE: String = "image/*"
val M_MUSIC: String = "audio/*"
val M_VIDEO: String = "video/*"
val M_TEXT: String = "text/*"
val M_APPLICATION: String = "application/*"

private val weChatPackage = "com.tencent.mm.ui.tools."
/**
 * 微信
 */
val WECHAT: (String, String) -> Boolean = { _, activityName ->
    activityName == "${weChatPackage}ShareImgUI"
            || activityName == "${weChatPackage}AddFavoriteUI"
            || activityName == "${weChatPackage}ShareToTimeLineUI"
}
/**
 * 微信朋友
 */
val WECHAT_FRIENDS: (String, String) -> Boolean =
    { _, activityName -> activityName == "${weChatPackage}ShareImgUI" }
/**
 * 微信朋友圈
 */
val WECHAT_CIRCLE: (String, String) -> Boolean =
    { _, activityName -> activityName == "${weChatPackage}ShareToTimeLineUI" }
/**
 * 微信收藏
 */
val WECHAT_COLLECTION: (String, String) -> Boolean =
    { _, activityName -> activityName == "${weChatPackage}AddFavoriteUI" }

val QQ: (String, String) -> Boolean = { _, activityName ->
    activityName == "com.tencent.mobileqq.activity.JumpActivity" //分享给朋友
            || activityName == "com.tencent.mobileqq.activity.qfileJumpActivity"//发送给我的电脑
            || activityName == "cooperation.qlink.QlinkShareJumpActivity"
            || activityName == "cooperation.qqfav.widget.QfavJumpActivity"////收藏
}

/**
 * 百度网盘
 */
val BAIDU_NETDISC: (String, String) -> Boolean = { _, activityName ->
    activityName == "com.baidu.netdisk.ui.EnterShareFileActivity"
            || activityName == "com.baidu.netdisk.p2pshare.ui.ReceiverP2PShareFileActivity"
}

/**
 * 百度网盘文件上传
 */
val BAIDU_NETDISC_UPLOAD: (String, String) -> Boolean = { _, activityName ->
    activityName == "com.baidu.netdisk.p2pshare.ui.ReceiverP2PShareFileActivity"
}


/**
 * 百度网盘笔记
 */
val BAIDU_NETDISC_NOTES: (String, String) -> Boolean = { _, activityName ->
    activityName == "com.baidu.netdisk.ui.EnterShareFileActivity"
}

/**
 * 快牙
 */
val KUAIYA: (String, String) -> Boolean = { _, activityName ->
    activityName == "com.dewmobile.kuaiya.act.DmStartupActivity"
}
/**
 * 夸克收藏
 */
val UCPRO_COLLECTION: (String, String) -> Boolean = { _, activityName ->
    activityName == "com.ucpro.feature.bookmarkhis.bookmark.thirdparty.AddFavoriteActivity"
}
/**
 * 夸克下载
 */
val UCPRO_DOWNLOAD: (String, String) -> Boolean = { _, activityName ->
    activityName == "com.ucpro.feature.downloadpage.thirdinvoke.QuarkDownloadForThirdParty"
}
/**
 * 微博
 */
val WEI_BO: (String, String) -> Boolean = { _, activityName ->
    activityName == "com.sina.weibo.composerinde.ComposerDispatchActivity"
            || activityName == "com.sina.weibo.story.publisher.StoryDispatcher"
            || activityName == "com.sina.weibo.weiyou.share.WeiyouShareDispatcher" //分享私信
}


/**
 * 发布动态
 */
val CSDN: (String, String) -> Boolean = { _, activityName ->
    activityName == "net.csdn.csdnplus.activity.PublishBlinActivity"
}
/**
 * 维基百科 搜索
 */
val WIKI_SEARCH: (String, String) -> Boolean = { _, activityName ->
    activityName == "org.wikipedia.search.SearchActivity"
}

/**
 * google keep
 */
val GOOGLE_KEEP: (String, String) -> Boolean = { _, activityName ->
    activityName == "com.google.android.keep.activities.ShareReceiverActivity"
}
/**
 * google tasts
 */
val GOOGLE_TASKS: (String, String) -> Boolean = { _, activityName ->
    activityName == "com.google.android.apps.tasks.ui.ShareWithTaskListsActivity"
}
/**
 * google 翻译
 */
val GOOGL_TRANSLATE: (String, String) -> Boolean = { _, activityName ->
    activityName == "com.google.android.apps.translate.TranslateActivity"
}

/**
 * Google
 */
val GOOGLE: (String, String) -> Boolean = { _, activityName ->
    activityName == "com.google.android.apps.translate.TranslateActivity"
            || activityName == "com.google.android.apps.tasks.ui.ShareWithTaskListsActivity"
            || activityName == "com.google.android.keep.activities.ShareReceiverActivity"
            || activityName == "com.google.android.youtube.UploadIntentHandlingActivity"
}

/**
 * 酷安 发布动态
 */
val COOLAPK: (String, String) -> Boolean = { _, activityName ->
    activityName == "com.coolapk.market.view.feedv8.ShareFeedV8Activity"
}
/**
 * 今日头条搜索
 */
val HEADLINE_SEARCH: (String, String) -> Boolean = { _, activityName ->
    activityName == "com.ss.android.article.base.feature.search.SearchActivity"
}
/**
 * 知乎想法
 */
val ZHIHU_IDEA: (String, String) -> Boolean = { _, activityName ->
    activityName == "com.zhihu.android.app.ui.activity.share.ShareToFeedActivity"
}
/**
 * 知乎私信
 */
val ZHIHU_PRIVATE_LETTER: (String, String) -> Boolean = { _, activityName ->
    activityName == "com.zhihu.android.app.ui.activity.share.ShareToMessageActivity"
}

/**
 * 知乎
 */
val ZHIHU: (String, String) -> Boolean = { _, activityName ->
    activityName == "com.zhihu.android.app.ui.activity.share.ShareToMessageActivity"
            || activityName == "com.zhihu.android.app.ui.activity.share.ShareToFeedActivity"
}

/**
 * 优酷
 */
val YOUKU: (String, String) -> Boolean = { _, activityName ->
    activityName == "com.youku.upload.activity.MyUploadVideoPageActivity"
}
/**
 * youtube
 */
val YOUTUBE: (String, String) -> Boolean = { _, activityName ->
    activityName == "com.google.android.youtube.UploadIntentHandlingActivity"
}
/**
 * 思维导图
 */
val XMIND: (String, String) -> Boolean = { _, activityName ->
    activityName == "net.xmind.doughnut.MainActivity"
}

/**
 * 这是原生的分享方法，可以分享文字，图片，音乐，文件等等。
 * @param content 分享的文字
 * @param mimeType：分享的内容的格式，默认是文本，可以设置图片类型，比如：image/jpeg
 * @param subject 分享的主题
 * @param uri 分享的多媒体内容，比如图片，文件等。
 * @param filter 过滤器，可以通过判断 appName或者activityName将分享精确到某些或者是某个应用
 */
fun Context.share(
    content: String = "",
    mimeType: String = M_TEXT,
    subject: String = "",
    uri: Uri? = null,
    filter: (appName: String, activityName: String) -> Boolean = { _, _ -> true }
) {
    val targeted = Intent(Intent.ACTION_SEND)
    targeted.type = mimeType
    val resInfo = packageManager.queryIntentActivities(targeted, PackageManager.MATCH_DEFAULT_ONLY)

    if (resInfo.isNotEmpty()) {
        val targetedShareIntents = ArrayList<Intent>()
        for (info in resInfo) {
            val target = targeted.clone() as Intent

            val activityInfo = info.activityInfo
            val appName =
                activityInfo.applicationInfo.loadLabel(applicationContext.packageManager).toString()
            val activityName = activityInfo.name


            if (filter(
                    appName,
                    activityName
                )
            ) {
                if (uri != null) {
                    target.putExtra(Intent.EXTRA_STREAM, uri)
                }

                target.putExtra(Intent.EXTRA_TEXT, content)
                target.putExtra(Intent.EXTRA_SUBJECT, subject)
                target.setPackage(activityInfo.packageName)
                target.setClassName(activityInfo.packageName, info.activityInfo.name)
                targetedShareIntents.add(target)
            }
        }
        if (targetedShareIntents.size == 0)
            showToast("找不到可以分享该类型内容的应用")

        try {
            val chooserIntent =
                Intent.createChooser(targetedShareIntents.removeAt(0), "分享") ?: return
            chooserIntent.putExtra(
                Intent.EXTRA_INITIAL_INTENTS,
                targetedShareIntents.toTypedArray<Parcelable>()
            )
            startActivity(chooserIntent)
        } catch (ex: Exception) {
            Log.e("tag", "$ex")
        }
    } else
        showToast("找不到可以分享该类型内容的应用")
}

fun Context.showToast(s: String) {
    Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
}

