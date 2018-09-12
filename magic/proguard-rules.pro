# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#============================
# Application 全局统一配置 start
#============================

# ----不做混淆优化----
-dontoptimize

# ----混淆时不会产生形形色色的类名----
-dontusemixedcaseclassnames

# ----指定不忽略非公共的库类----
-dontskipnonpubliclibraryclasses

# ----混淆包路径----
-repackageclasses 'com.aliyun.iot.ilop.page.share'

# ----优化时允许访问并修改有修饰符的类和类的成员----
-allowaccessmodification

# ----保护类型----
-keepattributes *Annotation*,SourceFile,Signature,LineNumberTable,InnerClasses

# ----打印混淆信息----
-printmapping map.txt


-keepattributes InnerClasses

# ----保留R文件----
-keep class **.R
-keep class **.R$* {
    <fields>;
}

#============================
# android system
#============================
-dontwarn android.test.**
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class * extends java.lang.Enum {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#============================
# Application 全局统一配置 end
#============================

#============================
# MagicRecyclerView start
#============================

-keep public class * extends BaseData
-keep public class * extends BaseHolder

#============================
# MagicRecyclerView end
#============================

-keep class com.wesksky.magicrecyclerview.**{*;}