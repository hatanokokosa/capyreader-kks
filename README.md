这是一个由 LLM 维护的自用 Capy Reader fork，修改了一些自己觉得比较麻烦的功能，该仓库不接受贡献

涉及的功能：
- 原先的 Capy Reader 不支持永久保留已读文章。在源码中有该选项，但其并没有发挥作用，本 Fork 将其开启
- 此外，由于习惯于浏览器阅读，同时默认的阅读器对我来说并不好用，因此移除，默认使用浏览器打开文章
- 目前就只有这两个调整，本 fork 将不跟随上游更新，也大概率不会做出贡献...

# Capy Reader

<img src="./site/capy.png" width="100px">

_A smallish RSS reader with support for Feedbin, FreshRSS, Miniflux and local feeds._

![Tests](https://github.com/jocmp/capyreader/actions/workflows/test.yml/badge.svg) <a href="https://hosted.weblate.org/engage/capy-reader/">
 <img src="https://hosted.weblate.org/widget/capy-reader/strings/svg-badge.svg" alt="Translation status" />
</a>

## Download

- [Google Play][gplay_link]
- [F-Droid][fdroid_link]
- [IzzyOnDroid][izzy_link]
- [GitHub Releases][github_link]

Capy Reader is free across all sources. It is available for devices running Android 11 or later.

### Releases

- Google Play and F-Droid releases happen every week or so depending on new features, bugfixes and translations.
- Nightly releases are a separate package marked with a "-nightly" suffix. These are unstable, untested builds.

## Reporting a bug

Bug reports are always welcome and are a top priority. Please search the [discussions](https://github.com/jocmp/capyreader/discussions) first and issues to make sure the bug is new and hasn't been reported yet.

If you're in doubt or run into an unreported issue, go ahead and submit a bug report. Please include details like app version, OS version, and steps to reproduce the bug. Screenshots are also useful!

[Report a bug](https://github.com/jocmp/capyreader/discussions/new?category=bugs)

## Feature Requests

If you have questions or a general feature request, please post them to discussions as a [feature request](https://github.com/jocmp/capyreader/discussions/new?category=feature-requests).

### Full Content Support

Sometimes Capy's full content mode doesn't work as expected on a site due to the chaos of the web.

When this happens, please submit a separate issue to support full content for that site: [Full Content Request form](https://github.com/jocmp/capyreader/discussions/categories/full-content-request)

Writing and verifying full content parsers can be time consuming. If you find this useful, [consider donating](https://ko-fi.com/capyreader) to show your support.

## Roadmap

Check out the [project roadmap](https://github.com/users/jocmp/projects/3) to see what's currently in progress and on deck.

## Contributing

### Translations

Translations are hosted on [Weblate](https://hosted.weblate.org/projects/capy-reader) and are open to everyone to add or update translations. If you don't see your language, feel free to add it!

### Pull Requests

If you want to create a pull request **please ask first.** This will save time in review, and save your time writing code if it doesn't fit for the app.

## Building the app

### Getting Started

1. Clone this repository
2. Install [Android Studio](https://developer.android.com/studio) if you don't have it already
3. Sync Gradle
4. In the toolbar, go to Run > Run 'app'

#### Build a signed release (Optional)

By default the app will build with a debug keystore. Follow the instructions below to build a signed release.

1. Ensure you have a keystore with the name `release.keystore` in the root directory.
2. Next, create a file called `secrets.properties`, also in the root directory, with the following values

    ```properties
    key_alias=
    store_password=
    key_password=
    ```


[gplay_link]: https://play.google.com/store/apps/details?id=com.capyreader.app
[fdroid_link]: https://f-droid.org/packages/com.capyreader.app/
[izzy_link]: https://apt.izzysoft.de/fdroid/index/apk/com.capyreader.app
[izzy_img]: https://img.shields.io/endpoint?url=https://apt.izzysoft.de/fdroid/api/v1/shield/com.capyreader.app&label=IzzyOnDroid
[github_link]: https://github.com/jocmp/capyreader/releases/latest
[github_img]: https://img.shields.io/github/v/release/jocmp/capyreader?logo=GitHub
