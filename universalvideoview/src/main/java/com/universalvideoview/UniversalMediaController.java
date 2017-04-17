package com.universalvideoview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.media.AudioManager;
import android.media.session.MediaController;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Formatter;
import java.util.Locale;

public class UniversalMediaController extends FrameLayout {
    public UniversalMediaController.MediaPlayerControl mPlayer;

    private PlayPrevNextListener mPlayPrevNextListener;

    private Context mContext;

    public SeekBar mProgress;

    public SeekBar mVolumeSeekbar;

    public TextView mEndTime, mCurrentTime;

    private TextView mTitle;

    public boolean mShowing = true;                                //控制栏是否显示

    public boolean mDragging;                                      //是否正在拖动

    private boolean mScalable = false;
    private boolean mIsFullScreen = false;
    public boolean mFullscreenEnabled = true;                     //是否可以全屏


    public static final int sDefaultTimeout = 3000;               //默认的延迟时间

    private static final int STATE_PLAYING = 1;
    private static final int STATE_PAUSE = 2;
    private static final int STATE_LOADING = 3;
    private static final int STATE_ERROR = 4;
    private static final int STATE_COMPLETE = 5;

    private int mState = STATE_LOADING;


    public static final int FADE_OUT = 1;
    public static final int SHOW_PROGRESS = 2;
    public static final int SHOW_LOADING = 3;
    public static final int HIDE_LOADING = 4;
    public static final int SHOW_ERROR = 5;
    public static final int HIDE_ERROR = 6;
    public static final int SHOW_COMPLETE = 7;
    public static final int HIDE_COMPLETE = 8;
    StringBuilder mFormatBuilder;

    Formatter mFormatter;

    public ImageButton mTurnButton;    // 开启暂停按钮

    public ImageButton mScaleButton;   //全屏按钮

    public ImageButton mPrevButton;    //上一视频

    public ImageButton mNextButton;    //下一视频

    public ImageButton mReplayButton;   //重新播放

    public ImageButton mStopButton;     //停止播放

    private View mBackButton;           // 返回按钮

    private ImageView mPlayerStopView;  //播放结束 或者停止播放时展示的view

    private ViewGroup loadingLayout;

    private ViewGroup errorLayout;

    private View mTitleLayout;
    private View mControlLayout;

    private View mCenterPlayButton;


    public  AudioManager audiomanage;               //音量控制
    private int maxVolume, currentVolume;           //音量最大值与当前值

    MyVolumeReceiver  mVolumeReceiver;

    public UniversalMediaController(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.UniversalMediaController);
        mScalable = a.getBoolean(R.styleable.UniversalMediaController_uvv_scalable, false);
        a.recycle();
        init(context);
    }

    public UniversalMediaController(Context context) {
        super(context);
        init(context);
    }

    /**
     * 上一首 下一首 重新播放的监听
     * @param mlistener
     */
    public void setPlayPrevNextListener(PlayPrevNextListener mlistener){
        this.mPlayPrevNextListener = mlistener;
    }

    /**
     * 载入控制器自定义布局的地方
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRoot = inflater.inflate(R.layout.my_player_controller, this);
        viewRoot.setOnTouchListener(mTouchListener);
        initControllerView(viewRoot);
    }

    /**
     * 初始化控制器控件的地方
     * @param v
     */
    private void initControllerView(View v) {
        mTitleLayout = v.findViewById(R.id.title_part);
        mControlLayout = v.findViewById(R.id.control_layout);
        loadingLayout = (ViewGroup) v.findViewById(R.id.loading_layout);
        errorLayout = (ViewGroup) v.findViewById(R.id.error_layout);
        mTurnButton = (ImageButton) v.findViewById(R.id.turn_button);
        mScaleButton = (ImageButton) v.findViewById(R.id.scale_button);
        mCenterPlayButton = v.findViewById(R.id.center_play_btn);
        mBackButton = v.findViewById(R.id.back_btn);

        mPrevButton = (ImageButton) v.findViewById(R.id.prev);
        mNextButton = (ImageButton) v.findViewById(R.id.next);

        mReplayButton = (ImageButton) v.findViewById(R.id.replay);
        mStopButton = (ImageButton) v.findViewById(R.id.stop);

        mPlayerStopView = (ImageView) v.findViewById(R.id.ivPlayerStop);

        //添加 停止播放时展示的图片的监听
        if (mPlayerStopView != null){
            mPlayerStopView.setOnClickListener(mReplayListener);
        }

        //添加暂停播放按钮的监听
        if (mTurnButton != null) {
            mTurnButton.requestFocus();
            mTurnButton.setOnClickListener(mPauseListener);
        }
        //添加上一视频播放按钮的监听
        if (mPrevButton != null) {
            mPrevButton.requestFocus();
            mPrevButton.setOnClickListener(mPrevListener);
        }

        //添加下一视频播放按钮的监听
        if (mNextButton != null) {
            mNextButton.requestFocus();
            mNextButton.setOnClickListener(mNextListener);
        }

        //添加重新播放按钮的监听
        if (mReplayButton != null){
            mReplayButton.requestFocus();
            mReplayButton.setOnClickListener(mReplayListener);
        }

        //添加停止播放按钮的监听
        if (mStopButton != null){
            mStopButton.requestFocus();
            mStopButton.setOnClickListener(mStopListener);
        }

        //控制全屏与否的属性 以及监听
        if (mScalable) {
            if (mScaleButton != null) {
                mScaleButton.setVisibility(VISIBLE);
                mScaleButton.setOnClickListener(mScaleListener);
            }
        } else {
            if (mScaleButton != null) {
                mScaleButton.setVisibility(GONE);
            }
        }

        //暂停时屏幕中央的播放按钮
        if (mCenterPlayButton != null) {//重新开始播放
            mCenterPlayButton.setOnClickListener(mCenterPlayListener);
        }

        if (mBackButton != null) {//返回按钮仅在全屏状态下可见
            mBackButton.setOnClickListener(mBackListener);
        }

        //视频播放进度控制
         mProgress = (SeekBar) v.findViewById(R.id.seekbar);
        if (mProgress != null) {
            mProgress.setOnSeekBarChangeListener(mSeekListener);
            mProgress.setMax(1000);
        }

        //添加音量控制
        View volumeBar = v.findViewById(R.id.sbVolumeSlider);
        mVolumeSeekbar = (SeekBar) volumeBar;
        initVolume(mVolumeSeekbar);
        mVolumeSeekbar.setOnSeekBarChangeListener(mVolumeSeekListener);
        mVolumeSeekbar.setMax(100);



        mEndTime = (TextView) v.findViewById(R.id.duration);
        mCurrentTime = (TextView) v.findViewById(R.id.has_played);
        mTitle = (TextView) v.findViewById(R.id.title);
        mFormatBuilder = new StringBuilder();
        mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());
    }


    private void initVolume(SeekBar seekBar){
        AudioManager audioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);//获取媒体系统服务
        seekBar.setMax(maxVolume); //设置最大音量
        currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);  //获取当前值
        seekBar.setProgress(currentVolume);// 当前的媒体音量
        myRegisterReceiver();//注册同步更新的广播
    }


    public void setMediaPlayer(MediaPlayerControl player) {
        mPlayer = player;
        updatePausePlay();
    }

    /**
     * Show the controller on screen. It will go away
     * automatically after 3 seconds of inactivity.
     */
    public void show() {
            show(sDefaultTimeout);
    }

    /**
     * Disable pause or seek buttons if the stream cannot be paused or seeked.
     * This requires the control interface to be a MediaPlayerControlExt
     */
    private void disableUnsupportedButtons() {
        try {
            if (mTurnButton != null && mPlayer != null && !mPlayer.canPause()) {
                mTurnButton.setEnabled(false);
            }
        } catch (IncompatibleClassChangeError ex) {
            // We were given an old version of the interface, that doesn't have
            // the canPause/canSeekXYZ methods. This is OK, it just means we
            // assume the media can be paused and seeked, and so we don't disable
            // the buttons.
        }
    }

    /**
     * Show the controller on screen. It will go away
     * automatically after 'timeout' milliseconds of inactivity.
     *
     * @param timeout The timeout in milliseconds. Use 0 to show
     *                the controller until hide() is called.
     */
    public void show(int timeout) {
        //非全屏状态下不显示控制条
        if (!mIsFullScreen){
//            unMyRegisterReceiver();
            return;
        }

        //只负责上下两条bar的显示,不负责中央loading,error,playBtn的显示.
        if (!mShowing) {
            setProgress();
            if (mTurnButton != null) {
                mTurnButton.requestFocus();
            }
            disableUnsupportedButtons();
            mShowing = true;
        }
        updatePausePlay();
        updateBackButton();

        if (getVisibility() != VISIBLE) {
            setVisibility(VISIBLE);
        }
        if (mTitleLayout.getVisibility() != VISIBLE) {
            mTitleLayout.setVisibility(VISIBLE);
        }
        if (mControlLayout.getVisibility() != VISIBLE) {
            mControlLayout.setVisibility(VISIBLE);
        }

        // cause the progress bar to be updated even if mShowing
        // was already true. This happens, for example, if we're
        // paused with the progress bar showing the user hits play.
        mHandler.sendEmptyMessage(SHOW_PROGRESS);

        Message msg = mHandler.obtainMessage(FADE_OUT);
        if (timeout != 0) {
            mHandler.removeMessages(FADE_OUT);
            mHandler.sendMessageDelayed(msg, timeout);
        }
    }

    public boolean isShowing() {
        return mShowing;
    }

    /**
     * 隐藏控制器
     */
    public void hide() {
        //只负责上下两条bar的隐藏,不负责中央loading,error,playBtn的隐藏
        if (mShowing) {
            mHandler.removeMessages(SHOW_PROGRESS);
            mTitleLayout.setVisibility(GONE);
            mControlLayout.setVisibility(GONE);
            mShowing = false;
        }
    }

    /**
     * 负责控制器各方面显示
     */
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int pos;
            switch (msg.what) {
                case FADE_OUT: //1
                    hide();
                    break;
                case SHOW_PROGRESS: //2
                    pos = setProgress();
                    if (!mDragging && mShowing && mPlayer != null && mPlayer.isPlaying()) {
                        msg = obtainMessage(SHOW_PROGRESS);
                        sendMessageDelayed(msg, 1000 - (pos % 1000));
                    }
                    break;
                case SHOW_LOADING: //3
                    show();
                    showCenterView(R.id.loading_layout);
                    break;
                case SHOW_COMPLETE: //7
                    showCenterView(R.id.ivPlayerStop);
                    break;
                case SHOW_ERROR: //5
                    show();
                    showCenterView(R.id.error_layout);
                    break;
                case HIDE_LOADING: //4
                case HIDE_ERROR: //6
                case HIDE_COMPLETE: //8
                    hide();
                    hideCenterView();
                    break;
            }
        }
    };

    /**
     * 播放器中心的加载，暂停，错误提示的显示
     * @param resId
     */
    private void showCenterView(int resId) {
        if (resId == R.id.loading_layout) {
            if (loadingLayout.getVisibility() != VISIBLE) {
                loadingLayout.setVisibility(VISIBLE);
            }
            if (mCenterPlayButton.getVisibility() == VISIBLE) {
                mCenterPlayButton.setVisibility(GONE);
            }
            if (errorLayout.getVisibility() == VISIBLE) {
                errorLayout.setVisibility(GONE);
            }
        } else if (resId == R.id.center_play_btn) {
            // TODO: 2017/4/15 暂时做的处理是让中央播放按钮完全隐藏 后期再将其删除

            if (mCenterPlayButton.getVisibility() != VISIBLE) {
                mCenterPlayButton.setVisibility(GONE);
            }
            if (loadingLayout.getVisibility() == VISIBLE) {
                loadingLayout.setVisibility(GONE);
            }
            if (errorLayout.getVisibility() == VISIBLE) {
                errorLayout.setVisibility(GONE);
            }

        } else if (resId == R.id.error_layout) {
            if (errorLayout.getVisibility() != VISIBLE) {
                errorLayout.setVisibility(VISIBLE);
            }
            if (mCenterPlayButton.getVisibility() == VISIBLE) {
                mCenterPlayButton.setVisibility(GONE);
            }
            if (loadingLayout.getVisibility() == VISIBLE) {
                loadingLayout.setVisibility(GONE);
            }

        }else if (resId == R.id.ivPlayerStop){
            hide();

            //新添加的播放结束或者停止播放时的显示界面
            if (mPlayerStopView.getVisibility() != VISIBLE){
                mPlayerStopView.setVisibility(VISIBLE);
            }

            if (errorLayout.getVisibility() == VISIBLE) {
                errorLayout.setVisibility(GONE);
            }
            if (mCenterPlayButton.getVisibility() == VISIBLE) {
                mCenterPlayButton.setVisibility(GONE);
            }
            if (loadingLayout.getVisibility() == VISIBLE) {
                loadingLayout.setVisibility(GONE);
            }

        }
    }


    /**
     * 播放器中心的加载，暂停，错误提示的隐藏
     */
    private void hideCenterView() {
        if (mCenterPlayButton.getVisibility() == VISIBLE) {
            mCenterPlayButton.setVisibility(GONE);
        }
        if (errorLayout.getVisibility() == VISIBLE) {
            errorLayout.setVisibility(GONE);
        }
        if (loadingLayout.getVisibility() == VISIBLE) {
            loadingLayout.setVisibility(GONE);
        }

        if (mPlayerStopView.getVisibility() == VISIBLE){
            mPlayerStopView.setVisibility(GONE);
        }
    }

    public void reset() {
        mCurrentTime.setText("00:00");
        mEndTime.setText("/00:00");
        mProgress.setProgress(0);
        mTurnButton.setImageResource(R.drawable.uvv_player_player_btn);
        setVisibility(View.VISIBLE);
        hideLoading();
    }



    //seekbar与播放器相联系的地方
    public int setProgress() {
        if (mPlayer == null || mDragging) {
            return 0;
        }
        int position = mPlayer.getCurrentPosition();
        int duration = mPlayer.getDuration();
        if (mProgress != null) {
            if (duration > 0) {
                // use long to avoid overflow
                long pos = 1000L * position / duration;
                mProgress.setProgress((int) pos);
            }
            int percent = mPlayer.getBufferPercentage();
            mProgress.setSecondaryProgress(percent * 10);
        }

        if (mEndTime != null)
            mEndTime.setText("/" + stringForTime(duration));
        if (mCurrentTime != null)
            mCurrentTime.setText(stringForTime(position));

        return position;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                show(0); // show until hide is called
                handled = false;
                break;
            case MotionEvent.ACTION_UP:
                if (!handled) {
                    handled = false;
                    show(sDefaultTimeout); // start timeout
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                hide();
                break;
            default:
                break;
        }
        return true;
    }

    boolean handled = false;
    //如果正在显示,则使之消失
    private OnTouchListener mTouchListener = new OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (mShowing) {
                    hide();
                    handled = true;
                    return true;
                }
            }
            return false;
        }
    };

    @Override
    public boolean onTrackballEvent(MotionEvent ev) {
        show(sDefaultTimeout);
        return false;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        final boolean uniqueDown = event.getRepeatCount() == 0
                && event.getAction() == KeyEvent.ACTION_DOWN;
        if (keyCode == KeyEvent.KEYCODE_HEADSETHOOK
                || keyCode == KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE
                || keyCode == KeyEvent.KEYCODE_SPACE) {
            if (uniqueDown) {
                doPauseResume();
                show(sDefaultTimeout);
                if (mTurnButton != null) {
                    mTurnButton.requestFocus();
                }
            }
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_MEDIA_PLAY) {
            if (uniqueDown && !mPlayer.isPlaying()) {
                mPlayer.start();
                updatePausePlay();
                show(sDefaultTimeout);
            }
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_MEDIA_STOP
                || keyCode == KeyEvent.KEYCODE_MEDIA_PAUSE) {
            if (uniqueDown && mPlayer.isPlaying()) {
                mPlayer.pause();
                updatePausePlay();
                show(sDefaultTimeout);
            }
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN
                || keyCode == KeyEvent.KEYCODE_VOLUME_UP
                || keyCode == KeyEvent.KEYCODE_VOLUME_MUTE
                || keyCode == KeyEvent.KEYCODE_CAMERA) {
            // don't show the controls for volume adjustment
            return super.dispatchKeyEvent(event);
        } else if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_MENU) {
            if (uniqueDown) {
                hide();
            }
            return true;
        }

        show(sDefaultTimeout);
        return super.dispatchKeyEvent(event);
    }

    private View.OnClickListener mPauseListener = new View.OnClickListener() {
        public void onClick(View v) {
            if (mPlayer != null) {
                doPauseResume();
                show(sDefaultTimeout);
            }
        }
    };

    //播放上一首
    private View.OnClickListener mPrevListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
//            Toast.makeText(mContext,"播放上一首",Toast.LENGTH_SHORT).show();
            if (mPlayPrevNextListener!=null){
                mPlayPrevNextListener.prev();
            }

        }
    };

    //播放下一首
    private View.OnClickListener mNextListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
//            Toast.makeText(mContext,"播放下一首",Toast.LENGTH_SHORT).show();
            if (mPlayPrevNextListener!=null){
                mPlayPrevNextListener.next();
            }

        }
    };

    //重新播放
    private View.OnClickListener mReplayListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            if (mPlayPrevNextListener!=null){
                mPlayPrevNextListener.rePlay();
            }
        }
    };

    //停止播放
    private View.OnClickListener mStopListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            if (mPlayer != null) {
                mPlayer.closePlayer();
            }

            if (mIsFullScreen) {
                mIsFullScreen = false;
                updateScaleButton();
                updateBackButton();
                mPlayer.setFullscreen(false);
            }

            showCenterView(R.id.ivPlayerStop);
            mFullscreenEnabled = false;

        }
    };


    private View.OnClickListener mScaleListener = new View.OnClickListener() {
        public void onClick(View v) {
            hide();
            mIsFullScreen = !mIsFullScreen;
            updateScaleButton();
            updateBackButton();
            mPlayer.setFullscreen(mIsFullScreen);
        }
    };

    //仅全屏时才有返回按钮
    private View.OnClickListener mBackListener = new View.OnClickListener() {
        public void onClick(View v) {
            if (mIsFullScreen) {
                mIsFullScreen = false;
                updateScaleButton();
                updateBackButton();
                mPlayer.setFullscreen(false);
            }

        }
    };

    private View.OnClickListener mCenterPlayListener = new View.OnClickListener() {
        public void onClick(View v) {
            hideCenterView();
            mPlayer.start();
        }
    };


    /**
     * 播放暂停按钮状态的切换
     */
    public void updatePausePlay() {
//        if (mPlayer != null && mPlayer.isPlaying()) {
//            mTurnButton.setImageResource(R.drawable.fullscreen_pause_press);
//            hideCenterView();
//        } else {
//            mTurnButton.setImageResource(R.drawable.fullscreen_play_press);
//            showCenterView(R.id.center_play_btn);
//        }

        if (mPlayer != null && mPlayer.isPlaying()) {
//            mTurnButton.setBackground(getResources().getDrawable(R.drawable.pause_selector_theme_btn) );
            mTurnButton.setBackgroundResource(R.drawable.f_pause_selector_theme_btn);
            hideCenterView();
        } else {
//            mTurnButton.setBackground(getResources().getDrawable(R.drawable.play_selector_theme_btn) );
            mTurnButton.setBackgroundResource(R.drawable.f_play_selector_theme_btn);
            showCenterView(R.id.center_play_btn);
        }
    }

    /**
     * 更新全屏切换的按钮
     */
    void updateScaleButton() {
        if (mIsFullScreen) {
//            mScaleButton.setImageResource(R.drawable.fullscreen_maxsize_press);
        } else {
//            mScaleButton.setImageResource(R.drawable.fullscreen_maxsize_press);
        }
    }

    /**
     * 全屏按钮与返回按钮的状态切换
     * @param isFullScreen
     */
    void toggleButtons(boolean isFullScreen) {
        mIsFullScreen = isFullScreen;
        updateScaleButton();
        updateBackButton();
    }


    /**
     * 更新返回的按钮
     */
    void updateBackButton() {
        mBackButton.setVisibility(mIsFullScreen ? View.VISIBLE : View.INVISIBLE);
    }

    boolean isFullScreen() {
        return mIsFullScreen;
    }

    /**
     * 播放器播放暂停切换
     */
    private void doPauseResume() {
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
        } else {
            mPlayer.start();
        }
        updatePausePlay();
    }

    /**
     * 视频播放到具体位置的seekbar监听
     */
    private OnSeekBarChangeListener mSeekListener = new OnSeekBarChangeListener() {
        int newPosition = 0;
        boolean change = false;

        public void onStartTrackingTouch(SeekBar bar) {
            if (mPlayer == null) {
                return;
            }
            show(3600000);

            mDragging = true;
            mHandler.removeMessages(SHOW_PROGRESS);
        }

        public void onProgressChanged(SeekBar bar, int progress, boolean fromuser) {
            if (mPlayer == null || !fromuser) {
                return;
            }

            long duration = mPlayer.getDuration();
            long newposition = (duration * progress) / 1000L;
            newPosition = (int) newposition;
            change = true;
        }

        public void onStopTrackingTouch(SeekBar bar) {
            if (mPlayer == null) {
                return;
            }
            if (change) {
                mPlayer.seekTo(newPosition);
                if (mCurrentTime != null) {
                    mCurrentTime.setText(stringForTime(newPosition));
                }
            }
            mDragging = false;
            setProgress();
            updatePausePlay();
            show(sDefaultTimeout);

            // Ensure that progress is properly updated in the future,
            // the call to show() does not guarantee this because it is a
            // no-op if we are already showing.
            mShowing = true;
            mHandler.sendEmptyMessage(SHOW_PROGRESS);
        }
    };

    /**
     * 音量控制的seekbar监听
     */
    private OnSeekBarChangeListener mVolumeSeekListener = new OnSeekBarChangeListener(){

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            AudioManager audioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
            //系统音量和媒体音量同时更新
            audioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, progress, 0);
            audioManager.setStreamVolume(3, progress, 0);//  3 代表  AudioManager.STREAM_MUSIC
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };



    @Override
    public void setEnabled(boolean enabled) {
//        super.setEnabled(enabled);
        if (mTurnButton != null) {
            mTurnButton.setEnabled(enabled);
        }
        if (mProgress != null) {
            mProgress.setEnabled(enabled);
        }
        if (mScalable) {
            mScaleButton.setEnabled(enabled);
        }
        mBackButton.setEnabled(true);// 全屏状态下右上角的返回键总是可用.
    }

    public void showLoading() {
        mHandler.sendEmptyMessage(SHOW_LOADING);
    }

    public void hideLoading() {
        mHandler.sendEmptyMessage(HIDE_LOADING);
    }

    public void showError() {
        mHandler.sendEmptyMessage(SHOW_ERROR);
    }

    public void hideError() {
        mHandler.sendEmptyMessage(HIDE_ERROR);
    }

    public void showComplete() {
        mHandler.sendEmptyMessage(SHOW_COMPLETE);
        if (mIsFullScreen) {
            mIsFullScreen = false;
            updateScaleButton();
            updateBackButton();
            mPlayer.setFullscreen(false);
        }

        mFullscreenEnabled = false;
    }

    public void hideComplete() {
        mHandler.sendEmptyMessage(HIDE_COMPLETE);
    }

    public void setTitle(String titile) {
        mTitle.setText(titile);
    }



    public void setOnErrorView(int resId) {
        errorLayout.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        inflater.inflate(resId, errorLayout, true);
    }

    public void setOnErrorView(View onErrorView) {
        errorLayout.removeAllViews();
        errorLayout.addView(onErrorView);
    }

    public void setOnLoadingView(int resId) {
        loadingLayout.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        inflater.inflate(resId, loadingLayout, true);
    }

    public void setOnLoadingView(View onLoadingView) {
        loadingLayout.removeAllViews();
        loadingLayout.addView(onLoadingView);
    }

    public void setOnErrorViewClick(View.OnClickListener onClickListener) {
        errorLayout.setOnClickListener(onClickListener);
    }

    public interface MediaPlayerControl {
        void start();

        void pause();

        int getDuration();

        int getCurrentPosition();

        void seekTo(int pos);

        boolean isPlaying();

        int getBufferPercentage();

        boolean canPause();

        boolean canSeekBackward();

        boolean canSeekForward();

        void closePlayer();//关闭播放视频,使播放器处于idle状态

        void setFullscreen(boolean fullscreen);

        /***
         *
         * @param fullscreen
         * @param screenOrientation valid only fullscreen=true.values should be one of
         *                          ActivityInfo.SCREEN_ORIENTATION_PORTRAIT,
         *                          ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE,
         *                          ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT,
         *                          ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE
         */
        void setFullscreen(boolean fullscreen, int screenOrientation);
    }

    /**
     * 未外界提供上一首 下一首  重新播放具体实现的回调接口
     */
    public interface PlayPrevNextListener {
        void prev();
        void next();

        void rePlay();
    }


    /**
     * 注册当音量发生变化时接收的广播
     */
    private void myRegisterReceiver(){
        mVolumeReceiver = new MyVolumeReceiver() ;
        IntentFilter filter = new IntentFilter() ;
        filter.addAction("android.media.VOLUME_CHANGED_ACTION") ;
        mContext.registerReceiver(mVolumeReceiver, filter) ;
    }


    /**
     * 取消注册当音量发生变化时接收的广播
     */
    public void unMyRegisterReceiver(){
        if (mVolumeReceiver != null){
            mContext.unregisterReceiver(mVolumeReceiver);
            mVolumeReceiver = null;
        }
    }

    /**
     * 处理音量变化时的界面显示
     * @author long
     */
    private class MyVolumeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //如果音量发生变化则更改seekbar的位置
            if(intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION")){
                AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                int currVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC) ;// 当前的媒体音量
                mVolumeSeekbar.setProgress(currVolume);
            }
        }
    }


    public String stringForTime(int timeMs) {
        int totalSeconds = timeMs / 1000;

        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        mFormatBuilder.setLength(0);
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }

}
