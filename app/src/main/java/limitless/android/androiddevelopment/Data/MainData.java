package limitless.android.androiddevelopment.Data;

import java.util.ArrayList;
import java.util.List;

import limitless.android.androiddevelopment.Activity.Basic.Activity.ActivityActivity;
import limitless.android.androiddevelopment.Activity.Basic.BroadCast.BasicBroadcastReceiver;
import limitless.android.androiddevelopment.Activity.Basic.BroadCast.BroadcastInfoActivity;
import limitless.android.androiddevelopment.Activity.Basic.Database.RoomActivity;
import limitless.android.androiddevelopment.Activity.Basic.Notification.CustomNotificationActivity;
import limitless.android.androiddevelopment.Activity.Basic.Sensors.EnvironmentActivity;
import limitless.android.androiddevelopment.Activity.Basic.Sensors.Network.JsonActivity;
import limitless.android.androiddevelopment.Activity.Basic.Notification.NotificationStylingActivity;
import limitless.android.androiddevelopment.Activity.Basic.Sensors.PositionActivity;
import limitless.android.androiddevelopment.Activity.Basic.FileStorage.RWCDFileActivity;
import limitless.android.androiddevelopment.Activity.Basic.FileStorage.ReadSongsActivity;
import limitless.android.androiddevelopment.Activity.Basic.FileStorage.ReadPhotosActivity;
import limitless.android.androiddevelopment.Activity.Basic.FileStorage.ReadVideosActivity;
import limitless.android.androiddevelopment.Activity.Basic.Database.RealmTestActivity;
import limitless.android.androiddevelopment.Activity.Basic.Sensors.Network.RetrofitActivity;
import limitless.android.androiddevelopment.Activity.Basic.BroadCast.SMSReceiverActivity;
import limitless.android.androiddevelopment.Activity.Basic.BroadCast.SetAlarmActivity;
import limitless.android.androiddevelopment.Activity.Basic.Database.SharePrefActivity;
import limitless.android.androiddevelopment.Activity.Basic.Service.SimpleImageDownloaderActivity;
import limitless.android.androiddevelopment.Activity.Basic.Database.SQLiteActivity;
import limitless.android.androiddevelopment.Activity.Basic.FileStorage.FileProviderActivity;
import limitless.android.androiddevelopment.Activity.Basic.Sensors.Network.VolleyActivity;
import limitless.android.androiddevelopment.Activity.CodeMore.Animation.AnimationFadeActivity;
import limitless.android.androiddevelopment.Activity.CodeMore.Animation.AnimationRotateActivity;
import limitless.android.androiddevelopment.Activity.CodeMore.Animation.AnimationSlideActivity;
import limitless.android.androiddevelopment.Activity.CodeMore.Animation.AnimationZoomActivity;
import limitless.android.androiddevelopment.Activity.CodeMore.AudioManagerActivity;
import limitless.android.androiddevelopment.Activity.CodeMore.AudioRecorderActivity;
import limitless.android.androiddevelopment.Activity.CodeMore.BluetoothActivity;
import limitless.android.androiddevelopment.Activity.CodeMore.ClipboardManagerActivity;
import limitless.android.androiddevelopment.Activity.CodeMore.GoogleMap.DarkStyleMapActivity;
import limitless.android.androiddevelopment.Activity.CodeMore.GoogleMap.StyledMapActivity;
import limitless.android.androiddevelopment.Activity.CodeMore.GoogleMap.SimpleMapsActivity;
import limitless.android.androiddevelopment.Activity.CodeMore.MediaPlayerActivity;
import limitless.android.androiddevelopment.Activity.CodeMore.TextToSpeechActivity;
import limitless.android.androiddevelopment.Activity.Basic.Sensors.Network.HttpRequestActivity;
import limitless.android.androiddevelopment.Activity.Basic.Notification.BasicNotificationActivity;
import limitless.android.androiddevelopment.Activity.Basic.Manifest.PermissionActivity;
import limitless.android.androiddevelopment.Activity.Basic.Security.SecurityActivity;
import limitless.android.androiddevelopment.Activity.Basic.Sensors.MotionActivity;
import limitless.android.androiddevelopment.Activity.Basic.Service.ServiceActivity;
import limitless.android.androiddevelopment.Activity.Basic.Thread.HandlerActivity;
import limitless.android.androiddevelopment.Activity.UIMore.About.AboutRestaurantActivity;
import limitless.android.androiddevelopment.Activity.UIMore.About.AboutInfiniteDevelopersActivity;
import limitless.android.androiddevelopment.Activity.UIMore.About.AboutFullScreenActivity;
import limitless.android.androiddevelopment.Activity.UIMore.About.AboutApplicationActivity;
import limitless.android.androiddevelopment.Activity.UIMore.Article.ArticleFullScreenActivity;
import limitless.android.androiddevelopment.Activity.UIMore.Article.ArticleBigHeaderActivity;
import limitless.android.androiddevelopment.Activity.UIMore.Article.ArticleShortTextActivity;
import limitless.android.androiddevelopment.Activity.UIMore.Article.ArticleCodingActivity;
import limitless.android.androiddevelopment.Activity.UIMore.Article.ArticleWithVideoActivity;
import limitless.android.androiddevelopment.Activity.UIMore.Chat.Chat1Activity;
import limitless.android.androiddevelopment.Activity.UIMore.Chat.Chat2Activity;
import limitless.android.androiddevelopment.Activity.UIMore.Chat.Chat3Activity;
import limitless.android.androiddevelopment.Activity.UIMore.Chat.Chat4Activity;
import limitless.android.androiddevelopment.Activity.UIMore.Chat.Chat5Activity;
import limitless.android.androiddevelopment.Activity.CodeMore.ImageEffectsActivity;
import limitless.android.androiddevelopment.Activity.UIMore.Login.LoginCardViewActivity;
import limitless.android.androiddevelopment.Activity.UIMore.Login.LoginSimpleActivity;
import limitless.android.androiddevelopment.Activity.UIMore.Login.LoginWithDetailsActivity;
import limitless.android.androiddevelopment.Activity.UIMore.Login.LoginWithTabActivity;
import limitless.android.androiddevelopment.Activity.UIMore.Music.MusicSimpleActivity;
import limitless.android.androiddevelopment.Activity.UIMore.Payment.Payment1Activity;
import limitless.android.androiddevelopment.Activity.UIMore.Payment.Payment2Activity;
import limitless.android.androiddevelopment.Activity.UIMore.Payment.Payment3Activity;
import limitless.android.androiddevelopment.Activity.UIMore.Payment.Payment4Activity;
import limitless.android.androiddevelopment.Activity.UIMore.Payment.Payment5Activity;
import limitless.android.androiddevelopment.Activity.UIMore.Payment.Payment6Activity;
import limitless.android.androiddevelopment.Activity.UIMore.Chat.TelegramActivity;
import limitless.android.androiddevelopment.Activity.UIMore.Profile.ProfileBigHeaderActivity;
import limitless.android.androiddevelopment.Activity.UIMore.Profile.ProfileDeveloperActivity;
import limitless.android.androiddevelopment.Activity.UIMore.Profile.ProfileTelegramActivity;
import limitless.android.androiddevelopment.Activity.UIMore.Profile.ProfileWithPostsActivity;
import limitless.android.androiddevelopment.Activity.UIMore.TaskManager.TaskManagerChartViewActivity;
import limitless.android.androiddevelopment.Activity.UIMore.TaskManager.TaskManagerListAndRecentlyActivity;
import limitless.android.androiddevelopment.Activity.UIMore.TaskManager.TaskManagerListsActivity;
import limitless.android.androiddevelopment.Activity.UIMore.TaskManager.TaskManagerNewActivity;
import limitless.android.androiddevelopment.Activity.UIMore.Verification.VerificationCodeActivity;
import limitless.android.androiddevelopment.Activity.UIMore.Verification.VerificationCustomKeyboardDarkActivity;
import limitless.android.androiddevelopment.Activity.UIMore.Verification.VerificationCustomKeyboardLightActivity;
import limitless.android.androiddevelopment.Activity.UIMore.Verification.VerificationPhoneActivity;
import limitless.android.androiddevelopment.Activity.UIMore.Chat.WhatAppActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.AppbarBottom.AppbarBottomActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.AppbarTop.AppbarTopActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.BottomSheetActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.ButtonsActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.CardsActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.ChipsActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.CustomComponents.CircleImageViewActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.Layouts.ConstraintLayoutActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.CustomComponents.CustomTextViewActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.DialogActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.Resources.DrawablesActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.Resources.FilesActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.FloatingActionButtonActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.Resources.FontsActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.Fragments.FragmentLifecycleActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.Fragments.FragmentManagerActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.Layouts.FrameLayoutActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.Layouts.LinearLayoutActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.List.ListActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.MenuDrawer.MenuActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.ProgressBarActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.Layouts.RelativeLayoutActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.CustomComponents.ShapeViewActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.SnackbarActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.Resources.StringActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.Tablayout.TablayoutActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.Layouts.TableLayoutActivity;
import limitless.android.androiddevelopment.Activity.UserInterface.CustomComponents.ValueSelectorActivity;
import limitless.android.androiddevelopment.Model.MainModel;
import limitless.android.androiddevelopment.Model.ProjectModel;
import limitless.android.androiddevelopment.Other.AnimationManager;
import limitless.android.androiddevelopment.R;

/**
 * Main data
 */
public class MainData {

    /**
    * list of components
    *   1.app manifest
    *   2.broadcast receiver
    *   3.service
    *   4.activity
    *   5.file storage
    *   6.data storage
    *   7.notification
    *   8.thread handling
    *   9.network handling
    *   10.sensors
    *   11.security
    */
    public static List<MainModel> components(){
        List<MainModel> mainList = new ArrayList<>();
        mainList.add(new MainModel("App Manifest", R.drawable.ic_manifest_512dp, false, false, manifest(), null, true));
        mainList.add(new MainModel( "Broadcast receiver", R.drawable.ic_broadcast_512dp, false, false, broadcastReceiver(), null, true));
        mainList.add(new MainModel("Service", R.drawable.ic_service_512dp, false, false, service(), null, true));
        mainList.add(new MainModel("Activity", R.drawable.ic_activity_512dp, false, false, activity(), null, true));
        mainList.add(new MainModel("File storage", R.drawable.ic_file_storage_512dp, false, false, fileStorage(), null, true));
        mainList.add(new MainModel("Data storage", R.drawable.ic_database_512dp, false, false, databaseStorage(), null, true));
        mainList.add(new MainModel("Notification", R.drawable.ic_notification_512dp, false, false, notification(), null, true));
//        mainList.add(new MainModel("Thread handling", R.drawable.ic_thread_512dp, false, false, thread(), null, false));
        mainList.add(new MainModel("Network handling", R.drawable.ic_network_512dp, false, false, network(), null, true));
        mainList.add(new MainModel("Sensors", R.drawable.ic_sensor_512dp, false, false, sensor(), null, true));
//        mainList.add(new MainModel("Security", R.drawable.ic_security_512dp, false, false, security(), null, false)); // TODO and in next versions

        return mainList;
    }

    /**
     *list of user interface
     *   1.ui components
     *   2.ui layouts
     *   3.resources
     *   4.fragments
     *   5.style and themes
     *   6.custom components
     */
    public static List<MainModel> userInterface() {
        List<MainModel> model = new ArrayList<>();
        model.add(new MainModel("UI components", R.drawable.ic_ui_components_512dp, false, false, uiComponents(), null, true));
        model.add(new MainModel("UI layouts", R.drawable.ic_layout_512dp, false, false, layout(), null, true));
        model.add(new MainModel("Resources", R.drawable.ic_resources_512dp, false, false, resources(), null, true));
        model.add(new MainModel("Fragments", R.drawable.ic_fragment_512dp, false, false, fragment(), null, true));
        model.add(new MainModel("Style and Themes", R.drawable.ic_theme_512dp, false, false, theme(), null, false));
        model.add(new MainModel("Custom components", R.drawable.ic_custom_512dp, false, false, custom(), null, true));
        return model;
    }

    /**
     * list of ui more
     *  1.chat
     *  2.about
     *  3.article
     *  4.dashboard
     *  5.payment
     *  6.login
     *  7.verification
     *  8.setting
     *  9.slider image
     *  10.search page
     *  11.shopping
     *  14.video
     *  15.profile
     *  16.books
     *  17.cooking
     *  18.education
     *  19.files
     *  20.files
     *  21.navigation
     *  22.news
     *  23.social
     *  24.music
     *  26.task manager
     *  27.travel
     * */
    public static List<MainModel> uiMore() {
        List<MainModel> model = new ArrayList<>();
        model.add(new MainModel("About", R.drawable.ic_about_512dp, false, false, about(), null, true));
        model.add(new MainModel("Article", R.drawable.ic_article_512dp, false, false, article(), null, true));
        model.add(new MainModel("Login", R.drawable.ic_login_512dp, false, false, login(), null, true));
        model.add(new MainModel("Verification", R.drawable.ic_verfication_512dp, false, false, verification(), null, true));
        model.add(new MainModel("Profile", R.drawable.ic_profile_512dp, false, false, profile(), null, true));
        model.add(new MainModel("Task Manager", R.drawable.ic_task_manager_512dp, false, false, taskManager(), null, true));

        model.add(new MainModel("Music", R.drawable.ic_music_512dp, false, false, null, null, false));
        model.add(new MainModel("Chat", R.drawable.ic_chat_512dp, false, false, chats(), null, false));
        model.add(new MainModel("Search page", R.drawable.ic_search_512dp, false, false, searchPage(), null, false));
        model.add(new MainModel("Dashboard", R.drawable.ic_dashboard_512dp, false, false, dashboard(), null, false));
        model.add(new MainModel("Payment", R.drawable.ic_payment_512dp, false, false, payment(), null, false));
        model.add(new MainModel("Setting", R.drawable.ic_settings_512dp, false, false, setting(), null, false));
        model.add(new MainModel("Slider image", R.drawable.ic_slider_image_512dp, false, false, sliderImage(), null, false));
        model.add(new MainModel("Shopping", R.drawable.ic_shopping_512dp, false, false, shopping(), null, false));
        model.add(new MainModel("Video", R.drawable.ic_video_512dp, false, false, videoPlayer(), null, false));
        model.add(new MainModel("Books", R.drawable.ic_books_512dp, false, false, books(), null, false));
        model.add(new MainModel("Cooking", R.drawable.ic_cooking_512dp, false, false, cooking(), null, false));
        model.add(new MainModel("Education", R.drawable.ic_education_512dp, false, false, education(), null, false));
        model.add(new MainModel("Files", R.drawable.ic_files_512dp, false, false, files(), null, false));
        model.add(new MainModel("Navigation", R.drawable.ic_navigation_512dp, false, false, navigation(), null, false));
        model.add(new MainModel("News", R.drawable.ic_news_512dp, false, false, news(), null, false));
        model.add(new MainModel("Social", R.drawable.ic_social_512dp, false, false, social(), null, false));
        model.add(new MainModel("Travel", R.drawable.ic_travel_512dp, false, false, travel(), null, false));
        return model;
    }

    public static List<MainModel> codeMore() {
        List<MainModel> model = new ArrayList<>();
        model.add(new MainModel("Clipboard", R.drawable.ic_clipboard_512dp, false,  false, null, ClipboardManagerActivity.class, false));
        model.add(new MainModel("Animations", R.drawable.ic_animation_512dp, false,  false, animations(), null, true));
        model.add(new MainModel("Audio capture", R.drawable.ic_audio_capture_512dp, false,  false, null, AudioRecorderActivity.class, false));
        model.add(new MainModel("Media player", R.drawable.ic_music_512dp, false,  false, null, MediaPlayerActivity.class, false));
        model.add(new MainModel("Text to speech", R.drawable.ic_text_to_speech_512dp, false,  false, null, TextToSpeechActivity.class, false));
        model.add(new MainModel("Audio manger", R.drawable.ic_audio_manager_512dp, false,  false, null, AudioManagerActivity.class, false));
        model.add(new MainModel("Bluetooth", R.drawable.ic_bluetooth_512dp, false,  false, null, BluetoothActivity.class, false));
        model.add(new MainModel("Image effects", R.drawable.ic_effects_512dp, false,  false, imageEffects(), null, true));
        model.add(new MainModel("Google maps", R.drawable.ic_google_maps_512dp, false,  false, googleMaps(), null, true));

        model.add(new MainModel("Firebase", R.drawable.ic_firebase_512dp, false,  false, firebase(), null, true));
        model.add(new MainModel("GPS", R.drawable.ic_maps_512dp, false,  false, null, null, false));
        model.add(new MainModel("Camera", R.drawable.ic_camera_512dp, false,  false, null, null, false));
        model.add(new MainModel("Gestures", R.drawable.ic_gestures_512dp, false,  false, null, null, false));
        model.add(new MainModel("Multi touch", R.drawable.ic_multi_touch_512dp, false,  false, null, null, false));
        model.add(new MainModel("Network connection", R.drawable.ic_network_connection_512dp, false,  false, null, null, false));
        model.add(new MainModel("Textur View", R.drawable.ic_texturview_512dp, false,  false, null, null, false));
        model.add(new MainModel("Wifi", R.drawable.ic_wifi_512dp, false,  false, null, null, false));
        return model;
    }

    private static List<MainModel.Inner> firebase() {
        List<MainModel.Inner> list = new ArrayList<>();
        list.add(new MainModel.Inner("Authentication", false, null));
        list.add(new MainModel.Inner("Cloud Storage", false, null));
        list.add(new MainModel.Inner("Realtime Database", false, null));
        list.add(new MainModel.Inner("Cloud Messaging", false, null));
        list.add(new MainModel.Inner("In-App Messaging", false, null));
        return list;
    }

    /**
     * @return list of project for sale
     */
    public static List<ProjectModel> projectList() {
        List<ProjectModel> list = new ArrayList<>();
        list.add(new ProjectModel("App manager", "Manager your apks in android", null, "https://play.google.com/store/apps/details?id=limitless.android.appmanager", null, 20, R.drawable.wallpaper_app_manager ));
        list.add(new ProjectModel("Color palette", "Material colors code and etc work with colors", null, "https://play.google.com/store/apps/details?id=limitless.materialcolor", null, 25, R.drawable.ic_color_pallete_sale));

//        list.add(new ProjectModel("Infinite wallpaper", "Get free wallpaper", null, null, null, 25, R.drawable.image_code_header));
//        list.add(new ProjectModel("File manager", "Manager your file in android", null, null, null, 30, R.drawable.ic_color_pallete_sale));
//        list.add(new ProjectModel("FlashLight", "Control your flash", null, null, null, 20, R.drawable.image_code_header));
//        list.add(new ProjectModel("Media downloader", "Download your media from twitter", null, null, null, 25, R.drawable.image_code_header));
//        list.add(new ProjectModel("Twitter unfollowers", "Best twitter unfollowers", null, null, null, 25, R.drawable.image_code_header));
        return list;
    }

    /**
     * @return List of Google map
     */
    private static List<MainModel.Inner> googleMaps() {
        List<MainModel.Inner> list = new ArrayList<>();
        list.add(new MainModel.Inner("Simple map", false, SimpleMapsActivity.class));
        list.add(new MainModel.Inner("Dark mode", false, DarkStyleMapActivity.class));
        list.add(new MainModel.Inner("Styled map", false, StyledMapActivity.class));
//        list.add(new MainModel.InnerModel("Live location", false, LiveLocationActivity.class));
        return list;
    }

    /**
     * @return a list of animation models
     * {@link AnimationManager}
     */
    private static List<MainModel.Inner> animations() {
        List<MainModel.Inner> models = new ArrayList<>();
        models.add(new MainModel.Inner("Zoom", false, AnimationZoomActivity.class));
        models.add(new MainModel.Inner("Slide", false, AnimationSlideActivity.class));
        models.add(new MainModel.Inner("Rotate", false, AnimationRotateActivity.class));
        models.add(new MainModel.Inner("Fade", false, AnimationFadeActivity.class));
        return models;
    }

    private static List<MainModel.Inner> imageEffects() {
        List<MainModel.Inner> models = new ArrayList<>();
        models.add(new MainModel.Inner("Grey Filter", false, ImageEffectsActivity.class));
        models.add(new MainModel.Inner("Sepia Filter", false, ImageEffectsActivity.class));
        models.add(new MainModel.Inner("Black and White Filter", false, ImageEffectsActivity.class));
        models.add(new MainModel.Inner("Brightness Filter", false, ImageEffectsActivity.class));
//        models.add(new MainModel.InnerModel("Sketch Filter", false, ImageEffectsActivity.class));
//        models.add(new MainModel.InnerModel("Brush Filter", false, ImageEffectsActivity.class));
        return models;
    }

    private static List<MainModel.Inner> travel() {
        List<MainModel.Inner> model = new ArrayList<>();

        return model;
    }

    private static List<MainModel.Inner> taskManager() {
        List<MainModel.Inner> model = new ArrayList<>();
        model.add(new MainModel.Inner("Lists", false, TaskManagerListsActivity.class));
        model.add(new MainModel.Inner("List & Recently", false, TaskManagerListAndRecentlyActivity.class));
        model.add(new MainModel.Inner("Chart View", false, TaskManagerChartViewActivity.class));
        model.add(new MainModel.Inner("New Task", false, TaskManagerNewActivity.class));
        return model;
    }

    private static List<MainModel.Inner> social() {
        List<MainModel.Inner> model = new ArrayList<>();

        return model;
    }

    private static List<MainModel.Inner> news() {
        List<MainModel.Inner> model = new ArrayList<>();

        return model;
    }

    private static List<MainModel.Inner> navigation() {
        List<MainModel.Inner> model = new ArrayList<>();

        return model;
    }

    private static List<MainModel.Inner> files() {
        List<MainModel.Inner> model = new ArrayList<>();

        return model;
    }

    private static List<MainModel.Inner> education() {
        List<MainModel.Inner> model = new ArrayList<>();

        return model;
    }

    private static List<MainModel.Inner> cooking() {
        List<MainModel.Inner> model = new ArrayList<>();

        return model;
    }

    private static List<MainModel.Inner> books() {
        List<MainModel.Inner> model = new ArrayList<>();

        return model;
    }

    /**
     * profile pages
     * 1. big header
     * 2. developer
     * 3. with posts
     * 4. telegram
     * */
    private static List<MainModel.Inner> profile() {
        List<MainModel.Inner> model = new ArrayList<>();
        model.add(new MainModel.Inner("Big Header", false, ProfileBigHeaderActivity.class));
        model.add(new MainModel.Inner("Developer", false, ProfileDeveloperActivity.class));
        model.add(new MainModel.Inner("With Posts", false, ProfileWithPostsActivity.class));
        model.add(new MainModel.Inner("Telegram", false, ProfileTelegramActivity.class));
        return model;
    }

    private static List<MainModel.Inner> music() {
        List<MainModel.Inner> model = new ArrayList<>();
        model.add(new MainModel.Inner("Simple", false, MusicSimpleActivity.class));
        return model;
    }

    private static List<MainModel.Inner> videoPlayer() {
        List<MainModel.Inner> model = new ArrayList<>();

        return model;
    }

    private static List<MainModel.Inner> shopping() {
        List<MainModel.Inner> model = new ArrayList<>();

        return model;
    }

    private static List<MainModel.Inner> searchPage() {
        List<MainModel.Inner> model = new ArrayList<>();

        return model;
    }

    private static List<MainModel.Inner> sliderImage() {
        List<MainModel.Inner> model = new ArrayList<>();

        return model;
    }

    private static List<MainModel.Inner> setting() {
        List<MainModel.Inner> model = new ArrayList<>();

        return model;
    }

    /**
     * Verification pages
     * 1. phone
     * 2. code
     * 3. custom keyboard light
     * 4. custom keyboard dark
    * */
    private static List<MainModel.Inner> verification() {
        List<MainModel.Inner> model = new ArrayList<>();
        model.add(new MainModel.Inner("Phone", false, VerificationPhoneActivity.class));
        model.add(new MainModel.Inner("Code", false, VerificationCodeActivity.class));
        model.add(new MainModel.Inner("Custom Keyboard Light", false, VerificationCustomKeyboardLightActivity.class));
        model.add(new MainModel.Inner("Custom Keyboard Dark", false, VerificationCustomKeyboardDarkActivity.class));
        return model;
    }

    private static List<MainModel.Inner> login() {
        List<MainModel.Inner> model = new ArrayList<>();
        model.add(new MainModel.Inner("Simple", false, LoginSimpleActivity.class));
        model.add(new MainModel.Inner("With Tab", false, LoginWithTabActivity.class));
        model.add(new MainModel.Inner("With Details", false, LoginWithDetailsActivity.class));
        model.add(new MainModel.Inner("Card View", false, LoginCardViewActivity.class));
        return model;
    }

    /**
     * Payments pages
     *  1. Payment 1
     *  2. Payment 2
     *  3. Payment 3
     *  4. Payment 4
     *  5. Payment 5
     *  6. Payment 6
     * */
    private static List<MainModel.Inner> payment() {
        List<MainModel.Inner> model = new ArrayList<>();
        model.add(new MainModel.Inner("Payment 1", false, Payment1Activity.class));
        model.add(new MainModel.Inner("Payment 2", false, Payment2Activity.class));
        model.add(new MainModel.Inner("Payment 3", false, Payment3Activity.class));
        model.add(new MainModel.Inner("Payment 4", false, Payment4Activity.class));
        model.add(new MainModel.Inner("Payment 5", false, Payment5Activity.class));
        model.add(new MainModel.Inner("Payment 6", false, Payment6Activity.class));
        return null;
    }

    private static List<MainModel.Inner> dashboard() {
        List<MainModel.Inner> model = new ArrayList<>();
        return model;
    }

    /**
     * article pages
     *  1. article 1
     *  2. article 2
     *  3. article 3
     *  4. article 4
     *  5. article 5
     *  */
    private static List<MainModel.Inner> article() {
        List<MainModel.Inner> model = new ArrayList<>();
        model.add(new MainModel.Inner("Full Screen", false, ArticleFullScreenActivity.class));
        model.add(new MainModel.Inner("Big Header", false, ArticleBigHeaderActivity.class));
        model.add(new MainModel.Inner("Short Text", false, ArticleShortTextActivity.class));
        model.add(new MainModel.Inner("Coding", false, ArticleCodingActivity.class));
        model.add(new MainModel.Inner("With Video", false, ArticleWithVideoActivity.class));
        return model;
    }

    /**
     * about pages
     *  1. About 1
     *  2. About 2
     *  3. About 3
     *  4. About 4
     * */
    private static List<MainModel.Inner> about() {
        List<MainModel.Inner> model = new ArrayList<>();
        model.add(new MainModel.Inner("Restaurant", false, AboutRestaurantActivity.class));
        model.add(new MainModel.Inner("Infinite Developers", false, AboutInfiniteDevelopersActivity.class));
        model.add(new MainModel.Inner("Full Screen", false, AboutFullScreenActivity.class));
        model.add(new MainModel.Inner("Application", false, AboutApplicationActivity.class));
        return model;
    }

    /**
     * chat pages
     *  1. Telegram
     *  2. WhatApp
     *  3. chat 1
     *  4. Chat 2
     *  5. Chat 3
     *  6. Chat 4
     *  5. Chat 5
     * */
    private static List<MainModel.Inner> chats() {
        List<MainModel.Inner> model = new ArrayList<>();
        model.add(new MainModel.Inner("Telegram", false, TelegramActivity.class));
        model.add(new MainModel.Inner("WhatApp", false, WhatAppActivity.class));
        model.add(new MainModel.Inner("Chat 1", false, Chat1Activity.class));
        model.add(new MainModel.Inner("Chat 2", false, Chat2Activity.class));
        model.add(new MainModel.Inner("Chat 3", false, Chat3Activity.class));
        model.add(new MainModel.Inner("Chat 4", false, Chat4Activity.class));
        model.add(new MainModel.Inner("Chat 5", false, Chat5Activity.class));
        return null;
    }

    /**
     * list of ui components
     *  1.app bar : bottom
     *  2.app bar : top
     *  3.backdrop
     *  4.banner
     *  5.bottom navigation
     *  6.buttons
     *  7.button : floating action button
     *  8.card
     *  9.chips
     *  10.data tables
     *  11.dialog
     *  12.dividers
     *  13.image list
     *  14.list
     *  15.menus
     *  16.navigation drawer
     *  17.pickers
     *  18.progress indicators
     *  19.selection controls : checkboxes
     *  20.selection controls : radio button
     *  21.selection controls : switches
     *  22.sheet bottom
     *  23.sheet side
     *  24.slider
     *  25.snackbars
     *  26.tabs
     *  27.text fields
     *  28.tooltips
     * */
    private static List<MainModel.Inner> uiComponents() {
        List<MainModel.Inner> model = new ArrayList<>();
        model.add(new MainModel.Inner("App bar : bottom", false, AppbarBottomActivity.class));
        model.add(new MainModel.Inner("App bar : top", false, AppbarTopActivity.class));
        model.add(new MainModel.Inner("Buttons", false, ButtonsActivity.class));
        model.add(new MainModel.Inner("Floating action button", false, FloatingActionButtonActivity.class));
        model.add(new MainModel.Inner("Cards", false, CardsActivity.class));
        model.add(new MainModel.Inner("Dialog", false, DialogActivity.class));
        model.add(new MainModel.Inner("List", false, ListActivity.class));
        model.add(new MainModel.Inner("Menus & Navigation drawer", false, MenuActivity.class));
        model.add(new MainModel.Inner("Progress bar", false, ProgressBarActivity.class));
        model.add(new MainModel.Inner("Bottom Sheet", false, BottomSheetActivity.class));
        model.add(new MainModel.Inner("SnackBars", false, SnackbarActivity.class));
        model.add(new MainModel.Inner("Tabs", false, TablayoutActivity.class));
        model.add(new MainModel.Inner("Chips", false, ChipsActivity.class));

//        model.add(new MainModel.InnerModel("Backdrop", false, BackdropActivity.class));
//        model.add(new MainModel.InnerModel("Banner", false, BannerActivity.class));
//        model.add(new MainModel.InnerModel("Dividers", false, UIComponentActivity.class));
//        model.add(new MainModel.InnerModel("Pickers", false, UIComponentActivity.class));
//        model.add(new MainModel.InnerModel("Sheet side", false, UIComponentActivity.class));
//        model.add(new MainModel.InnerModel("Slider", false, UIComponentActivity.class));
//        model.add(new MainModel.InnerModel("Text fields", false, UIComponentActivity.class));
//        model.add(new MainModel.InnerModel("Tooltips", false, UIComponentActivity.class));
//        model.add(new MainModel.InnerModel("Selection controls", false, UIComponentActivity.class));
        return model;
    }

    /**
     * list of ui layouts
     *  1.linear layout
     *  2.constraint layout
     *  3.relative layout
     *  4.frame layout
     *  5.table layout
     * */
    private static List<MainModel.Inner> layout() {
        List<MainModel.Inner> model = new ArrayList<>();
        model.add(new MainModel.Inner("Linear layout", false,  LinearLayoutActivity.class));
        model.add(new MainModel.Inner("Constraint layout", false,  ConstraintLayoutActivity.class));
        model.add(new MainModel.Inner("Relative layout", false,  RelativeLayoutActivity.class));
        model.add(new MainModel.Inner("Frame layout", false,  FrameLayoutActivity.class));
        model.add(new MainModel.Inner("Table layout", false,  TableLayoutActivity.class));
        return model;
    }

    /**
     * list of resources
     *  1.strings
     *  2.fonts
     *  3.drawables
     *  4.files
     * */
    private static List<MainModel.Inner> resources() {
        List<MainModel.Inner> model = new ArrayList<>();
        model.add(new MainModel.Inner("Strings", false,  StringActivity.class));
        model.add(new MainModel.Inner("Drawables", false,  DrawablesActivity.class));
        model.add(new MainModel.Inner("Files", false,  FilesActivity.class));
        model.add(new MainModel.Inner("Fonts", false,  FontsActivity.class));
        return model;
    }

    /**
     * list of fragment
     *  1.fragment lifecycle
     *  2.fragment manger
     *  3.fragment transaction
     * */
    private static List<MainModel.Inner> fragment() {
        List<MainModel.Inner> model = new ArrayList<>();
        model.add(new MainModel.Inner("Fragment lifecycle", false,  FragmentLifecycleActivity.class));
        model.add(new MainModel.Inner("Fragment manager", false,  FragmentManagerActivity.class));
//        model.add(new MainModel.InnerModel("Fragment transaction", false,  MainActivity.class));
        return model;
    }

    private static List<MainModel.Inner> theme() {
        List<MainModel.Inner> model = new ArrayList<>();
        return model;
    }

    /**
     * list of custom view
     *  1.simple custom text view
     *  2.shape view
     *  3.circle image view
     *  4.value selector
     *  5.count down
     * */
    private static List<MainModel.Inner> custom() {
        List<MainModel.Inner> model = new ArrayList<>();
        model.add(new MainModel.Inner("Simple custom text view",false, CustomTextViewActivity.class));
        model.add(new MainModel.Inner("Shape view", false,  ShapeViewActivity.class));
        model.add(new MainModel.Inner("Circle image view", false, CircleImageViewActivity.class));
        model.add(new MainModel.Inner("Value selector", false,  ValueSelectorActivity.class));
//        model.add(new MainModel.InnerModel("Count down", false,  CustomTextViewActivity.class));
        return model;
    }


    /**
     * list of security
     *  1.proguard
     *  2.R8
     *  3.Key store
     */
    private static List<MainModel.Inner> security() {
        List<MainModel.Inner> model = new ArrayList<>();
        model.add(new MainModel.Inner("Proguard", false, SecurityActivity.class));
        model.add(new MainModel.Inner("R8", false, SecurityActivity.class));
        model.add(new MainModel.Inner("Key store", false, SecurityActivity.class));
        return model;
    }

    /**
     *list fo sensors
     *  1.position
     *  2.environment
     *  3.motion
     *  4.camera
     */
    private static List<MainModel.Inner> sensor() {
        List<MainModel.Inner> model = new ArrayList<>();
        model.add(new MainModel.Inner("Motion sensors", false, MotionActivity.class));
        model.add(new MainModel.Inner("Position sensors", false, PositionActivity.class));
        model.add(new MainModel.Inner("Environment sensors", false, EnvironmentActivity.class));
        // TODO: 12/21/19  camera sensor add to next version
//        model.add(new MainModel.InnerModel("Camera", false, MotionActivity.class));
        return model;
    }


    /**
     * list of network
     *  1.http request
     *  2.volley
     *  3.retrofit
     *  4.json converters
     */
    private static List<MainModel.Inner> network() {
        List<MainModel.Inner> model = new ArrayList<>();
        model.add(new MainModel.Inner("Http request", false, HttpRequestActivity.class));
        model.add(new MainModel.Inner("Volley", false, VolleyActivity.class));
        model.add(new MainModel.Inner("Retrofit", false, RetrofitActivity.class));
        model.add(new MainModel.Inner("Json converters", false, JsonActivity.class));
        return model;
    }

    /**
    * list of thread
    *   1.handlers
    *   2.async task
    */
    private static List<MainModel.Inner> thread() {
        List<MainModel.Inner> model = new ArrayList<>();
        model.add(new MainModel.Inner("Handlers", false, HandlerActivity.class));
        model.add(new MainModel.Inner("Async Task", false, HandlerActivity.class));
        return model;
    }

    /**
    * List of notification
    *   1. Basic notification
    *   2. Notification Style
    *   3. Custom notification
    */
    private static List<MainModel.Inner> notification() {
        List<MainModel.Inner> model = new ArrayList<>();
        model.add(new MainModel.Inner("Basic notification", false, BasicNotificationActivity.class));
        model.add(new MainModel.Inner("Notification styling", false, NotificationStylingActivity.class));
        model.add(new MainModel.Inner("Custom notification", false, CustomNotificationActivity.class));
        return model;
    }

    /**
    * list of database storage
    *   1. SQLite
    *   2. Shared preferences
    *   3. Realm
    */
    private static List<MainModel.Inner> databaseStorage() {
        List<MainModel.Inner> model = new ArrayList<>();
        model.add(new MainModel.Inner("SQLite", false, SQLiteActivity.class));
        model.add(new MainModel.Inner("Shared preferences", false, SharePrefActivity.class));
        model.add(new MainModel.Inner("Realm", false, RealmTestActivity.class));
        model.add(new MainModel.Inner("Room", false, RoomActivity.class));
        model.add(new MainModel.Inner("ObjectBox", false, null)); // TODO: 4/27/20 add in other version
        return model;
    }

    /**
    * List of file storage
    *   1. File provider
    *   2. Read images
    *   3. Read audios
    *   4. Read videos
    *   5. Read & create & delete file
    */
    private static List<MainModel.Inner> fileStorage() {
        List<MainModel.Inner> model = new ArrayList<>();
        model.add(new MainModel.Inner("File provider", false, FileProviderActivity.class));
        model.add(new MainModel.Inner("Read photos", false, ReadPhotosActivity.class));
        model.add(new MainModel.Inner("Read songs", false, ReadSongsActivity.class));
        model.add(new MainModel.Inner("Read videos", false, ReadVideosActivity.class));
        model.add(new MainModel.Inner("Read & Write & Create & Delete file", false, RWCDFileActivity.class));
        return model;
    }

    /**
     * @return a list of activity models
    * list of activity
    *   1. Activity lifecycle
    */
    private static List<MainModel.Inner> activity() {
        List<MainModel.Inner> model = new ArrayList<>();
        model.add(new MainModel.Inner("Activity lifecycle", false, ActivityActivity.class));
        return model;
    }

    /**
     * @return  a list of service models
    * list of services
    *   1.basic service
    *   2.image downloader service
    *   3.set alarm service
    */
    private static List<MainModel.Inner> service() {
        List<MainModel.Inner> model = new ArrayList<>();

        model.add(new MainModel.Inner("Basic service", false, ServiceActivity.class));
        model.add(new MainModel.Inner("Image downloader service", false, SimpleImageDownloaderActivity.class));

        return model;
    }

    /**
     * @return a list of manifest models
    * list of manifest
    *   1. Permissions
    */
    private static List<MainModel.Inner> manifest(){
        List<MainModel.Inner> model = new ArrayList<>();
        model.add(new MainModel.Inner("Permissions", false, PermissionActivity.class));
        return model;
    }

    /**
     * @return a list of broadCast models
    * list of broadcasts
    *   1.basic
    *   2.info broadcast receiver
    *   3.sms received
    */
    private static List<MainModel.Inner> broadcastReceiver(){
        List<MainModel.Inner> br = new ArrayList<>();
        br.add(new MainModel.Inner("Info broadcast receiver", false, BroadcastInfoActivity.class));
        br.add(new MainModel.Inner("Basic broadcast receiver", false, BasicBroadcastReceiver.class));
//        br.add(new MainModel.Inner("SMS RECEIVED", false, SMSReceiverActivity.class));
        br.add(new MainModel.Inner("Set alarm", false, SetAlarmActivity.class));
        return br;
    }


}
