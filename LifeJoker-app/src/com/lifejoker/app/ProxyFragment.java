package com.lifejoker.app;

import com.lifejoker.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

/**
 * This fragment is used to show the third apps that are used to sign in/up
 * life-joker app.
 */
public class ProxyFragment extends Fragment implements OnTouchListener {
    public final static String TAG = ProxyFragment.class.getSimpleName();
    /**
     * This precision is used to scroll.
     */
    private final static float PRECISION = 10.5f;

    private Animation mPushDownIAnimation;
    private Animation mPushDownOAnimation;
    private Animation mPushUpIAnimation;
    private Animation mPushUpOAnimation;

    private ViewFlipper mProxiesView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPushDownIAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.push_down_in);
        mPushDownOAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.push_down_out);
        mPushUpIAnimation = AnimationUtils
                .loadAnimation(getActivity(), R.anim.push_up_in);
        mPushUpOAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.push_up_out);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_proxy, container, false);
        mProxiesView = (ViewFlipper) v.findViewById(R.id.proxies_views);

        v.findViewById(R.id.previous_button).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Switch to previous view.
                        showPrevious();
                    }
                });
        v.findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Switch to next view.
                showNext();
            }
        });

        // Set the default displayed view.
        int childIndex = 0;
        if (savedInstanceState != null) {
            childIndex = savedInstanceState.getInt("child_index");
        }
        mProxiesView.setDisplayedChild(childIndex);
        mProxiesView.setOnTouchListener(this);
        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("child_index", mProxiesView.getDisplayedChild());
        super.onSaveInstanceState(outState);
    }

    private float preY, preX;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            preX = event.getX();
            preY = event.getY();
            break;
        case MotionEvent.ACTION_MOVE:
            break;
        case MotionEvent.ACTION_UP:
            float curX = event.getX();
            float curY = event.getY();
            if (curY - preY > PRECISION || curX - preX > PRECISION) {
                // To show next.
                showNext();
            } else if (preY - curY > PRECISION || preX - curX > PRECISION) {
                // To show previous.
                showPrevious();
            }
            break;
        default:
            break;
        }
        return true;
    }

    public void showPrevious() {
        if (!mProxiesView.isFlipping()) {
            mProxiesView.setInAnimation(mPushDownIAnimation);
            mProxiesView.setOutAnimation(mPushDownOAnimation);
            mProxiesView.showPrevious();
        }
    }

    public void showNext() {
        if (!mProxiesView.isFlipping()) {
            mProxiesView.setInAnimation(mPushUpIAnimation);
            mProxiesView.setOutAnimation(mPushUpOAnimation);
            mProxiesView.showPrevious();
        }
    }
}
