package com.example.mousa.baking.Fragments;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.mousa.baking.Adapters.StepAdapter;
import com.example.mousa.baking.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import com.squareup.picasso.Picasso;
import static android.content.Context.MODE_PRIVATE;
import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
import static com.example.mousa.baking.Adapters.StepAdapter.po;

public class ShowDetail extends android.app.Fragment {
    private SimpleExoPlayer player;
    private PlayerView playerView;
    private long playbackPosition;
    private int currentWindow;
    private boolean playWhenReady = true;
    TextView Description;
    BottomNavigationView bottomNavigationView;
    Uri uri;
    String VideoUrl;
    String thunmbail;
    Uri url;
    ImageView imageView;
    SharedPreferences sharedPreferences;
    private static final DefaultBandwidthMeter BANDWIDTH_METER =
            new DefaultBandwidthMeter();
    private ComponentListener componentListener;

    ////////////Constructor////////////////////////////
    public ShowDetail() {
    }
//////////////////////////////////////////////////////


    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show, container, false);
        componentListener = new ComponentListener();
        playerView = (PlayerView) view.findViewById(R.id.videoview);
        Description = (TextView) view.findViewById(R.id.Description);
        Description.setText(StepAdapter.arrayList.get(StepAdapter.po).getDescription());
        bottomNavigationView = (BottomNavigationView) view.findViewById(R.id.navg);
        imageView = (ImageView) view.findViewById(R.id.thun);
        VideoUrl = StepAdapter.arrayList.get(StepAdapter.po).getVideoURL();
        thunmbail = StepAdapter.arrayList.get(StepAdapter.po).getThumbnailURL();
        sharedPreferences = getActivity().getSharedPreferences("myfile", MODE_PRIVATE);

        // For handle VideoUrl And Thunbnail///////////////////////////////
        if (!VideoUrl.isEmpty()) {
            playerView.setVisibility(View.VISIBLE);
            uri = Uri.parse(VideoUrl);


        } else if (!thunmbail.isEmpty()) {
            playerView.setVisibility(View.INVISIBLE);
            imageView.setVisibility(View.VISIBLE);
            Picasso.with(getActivity()).load(thunmbail).into(imageView);
            hideSystemUi();
        } else {
            playerView.setVisibility(View.GONE);
            imageView.setVisibility(View.GONE);
            uri = Uri.parse("");
        }

            /////////////////////////////////////////////////////////////


        ////////////////////////   Navigation Action //////////////////////////////////////////

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    //////////For AvtionNext//////////////////////////////////////////

                    case R.id.itemNext:
                        if (po < Frgmentt.stepAdapter.getItemCount() - 1) {
                            VideoUrl = StepAdapter.arrayList.get(po + 1).getVideoURL();
                            thunmbail = StepAdapter.arrayList.get(po + 1).getThumbnailURL();
                            Description.setText(StepAdapter.arrayList.get(po + 1).getDescription());
                            if (!VideoUrl.isEmpty()) {
                                playerView.setVisibility(View.VISIBLE);
                                url = Uri.parse(VideoUrl);
                                //hideSystemUi();
                                releasePlayer();
                                initializePlayer(url,true);


                            } else if (!thunmbail.isEmpty()) {
                                releasePlayer();
                                //hideSystemUi();
                                playerView.setVisibility(View.GONE);
                                imageView.setVisibility(View.VISIBLE);
                                Picasso.with(getActivity()).load(thunmbail).into(imageView);

                            } else {
                                releasePlayer();
                                //hideSystemUi();
                                playerView.setVisibility(View.GONE);
                                imageView.setVisibility(View.GONE);
                                uri = Uri.parse("");
                            }
                            po = po + 1;
                        }
                        break;
                    ///////////////////////////////////////////////////////////////////////

                    ////////////////For ActionBack/////////////////////////////

                    case R.id.itemPrevious:

                        if (po > 0) {

                            VideoUrl = StepAdapter.arrayList.get(po - 1).getVideoURL();
                            thunmbail = StepAdapter.arrayList.get(po - 1).getThumbnailURL();
                            Description.setText(StepAdapter.arrayList.get(po - 1).getDescription());

                            if (!VideoUrl.isEmpty()) {
                                playerView.setVisibility(View.VISIBLE);
                                imageView.setVisibility(View.GONE);
                                url = Uri.parse(VideoUrl);
                                releasePlayer();
                                initializePlayer(url,true);
                            } else if (!thunmbail.isEmpty()) {
                                releasePlayer();
                                playerView.setVisibility(View.GONE);
                                imageView.setVisibility(View.VISIBLE);
                                Picasso.with(getActivity()).load(thunmbail).into(imageView);
                            } else {
                                releasePlayer();
                                playerView.setVisibility(View.GONE);
                                imageView.setVisibility(View.GONE);
                                uri = Uri.parse("");
                            }
                            po = po - 1;
                        }
                        ///////////////////////////////////////////////////////////////////
                        break;
                }
            }
        });
        /////////////////////////////////////////////////////////////////////////////////////////////////



        return view;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("uri", String.valueOf(uri));
        outState.putString("url",String.valueOf(url));
        outState.putLong("pos", sharedPreferences.getLong("pos", 0));
        outState.putInt("cur", sharedPreferences.getInt("cur", 0));

        super.onSaveInstanceState(outState);
    }

// For PlayerView ////////////////////////////////////////////////////

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState!=null){
            playbackPosition = savedInstanceState.getLong("pos");
            currentWindow = savedInstanceState.getInt("cur");
            uri=Uri.parse(savedInstanceState.getString("uri"));
            url=Uri.parse(savedInstanceState.getString("url"));
            if(url!=null){
                initializePlayer(url,false);

            }

            initializePlayer(uri,false);
        }
    }



    @Override
    public void onStart() {
        super.onStart();
        if (player==null) {
            hideSystemUi();
            initializePlayer(uri,true);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        hideSystemUi();
        if (player == null) {
            initializePlayer(uri,true);
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
            SharedPreferences.Editor myedit = sharedPreferences.edit();
            myedit.putLong("pos", playbackPosition);
            myedit.putInt("cur", currentWindow);
            myedit.commit();

        }


    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
        SharedPreferences.Editor myedit = sharedPreferences.edit();
        myedit.putLong("pos", playbackPosition);
        myedit.putInt("cur", currentWindow);
        myedit.commit();

    }

    @Override
    public void onDetach() {
        releasePlayer();
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        releasePlayer();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        releasePlayer();
        super.onDestroy();
    }

    // InitialPlayer //////////////////////////////////////////////////
    private void initializePlayer(Uri uri,boolean check) {
        if (check) {
            // a factory to create an AdaptiveVideoTrackSelection
            TrackSelection.Factory adaptiveTrackSelectionFactory =
                    new AdaptiveTrackSelection.Factory(BANDWIDTH_METER);
            // using a DefaultTrackSelector with an adaptive video selection factory
            player = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(getActivity()),
                    new DefaultTrackSelector(adaptiveTrackSelectionFactory), new DefaultLoadControl());
            player.addListener(componentListener);
            player.addVideoDebugListener(componentListener);
            player.addAudioDebugListener(componentListener);
            playerView.setPlayer(player);
            player.setPlayWhenReady(playWhenReady);
            player.seekTo(currentWindow, playbackPosition);


            MediaSource mediaSource = buildMediaSource(uri);
            player.prepare(mediaSource, true, false);

        }
        else {

            TrackSelection.Factory adaptiveTrackSelectionFactory =
                    new AdaptiveTrackSelection.Factory(BANDWIDTH_METER);
            // using a DefaultTrackSelector with an adaptive video selection factory
            player = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(getActivity()),
                    new DefaultTrackSelector(adaptiveTrackSelectionFactory), new DefaultLoadControl());
            player.addListener(componentListener);
            player.addVideoDebugListener(componentListener);
            player.addAudioDebugListener(componentListener);
            playerView.setPlayer(player);
            player.setPlayWhenReady(true);
            player.seekTo(currentWindow,playbackPosition);
            MediaSource mediaSource = buildMediaSource(uri);
            player.prepare(mediaSource, false, false);

        }

   }

//////////////////////////////////////////////////////////////////////////

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("Baking")).
                createMediaSource(uri);
    }


    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.removeListener(componentListener);
            player.removeVideoDebugListener(componentListener);
            player.removeAudioDebugListener(componentListener);
            player.release();
            player=null;


        }
    }


    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        bottomNavigationView.setVisibility(View.VISIBLE);
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | SYSTEM_UI_FLAG_LAYOUT_STABLE
                | SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    private class ComponentListener extends Player.DefaultEventListener implements
            VideoRendererEventListener, AudioRendererEventListener {


        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

            String stateString;
            switch (playbackState) {
                case Player.STATE_IDLE:
                    stateString = "ExoPlayer.STATE_IDLE      -";
                    break;
                case Player.STATE_BUFFERING:
                    stateString = "ExoPlayer.STATE_BUFFERING -";
                    break;
                case Player.STATE_READY:
                    stateString = "ExoPlayer.STATE_READY     -";
                    break;
                case Player.STATE_ENDED:
                    stateString = "ExoPlayer.STATE_ENDED     -";
                    break;
                default:
                    stateString = "UNKNOWN_STATE             -";
                    break;
            }
        }

        // Implementing VideoRendererEventListener.

        @Override
        public void onVideoEnabled(DecoderCounters counters) {
            // Do nothing.
        }

        @Override
        public void onVideoDecoderInitialized(String decoderName, long initializedTimestampMs, long initializationDurationMs) {
            // Do nothing.
        }

        @Override
        public void onVideoInputFormatChanged(Format format) {
            // Do nothing.
        }

        @Override
        public void onDroppedFrames(int count, long elapsedMs) {
            // Do nothing.
        }

        @Override
        public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {
            // Do nothing.
        }

        @Override
        public void onRenderedFirstFrame(Surface surface) {
            // Do nothing.
        }

        @Override
        public void onVideoDisabled(DecoderCounters counters) {
            // Do nothing.
        }

        // Implementing AudioRendererEventListener.

        @Override
        public void onAudioEnabled(DecoderCounters counters) {
            // Do nothing.
        }

        @Override
        public void onAudioSessionId(int audioSessionId) {
            // Do nothing.
        }

        @Override
        public void onAudioDecoderInitialized(String decoderName, long initializedTimestampMs, long initializationDurationMs) {
            // Do nothing.
        }

        @Override
        public void onAudioInputFormatChanged(Format format) {
            // Do nothing.
        }

        @Override
        public void onAudioSinkUnderrun(int bufferSize, long bufferSizeMs, long elapsedSinceLastFeedMs) {
            // Do nothing.
        }

        @Override
        public void onAudioDisabled(DecoderCounters counters) {
            // Do nothing.
        }

    }
}
