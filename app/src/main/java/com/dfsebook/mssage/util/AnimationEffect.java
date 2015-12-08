package com.dfsebook.mssage.util;

import com.nineoldandroids.view.ViewHelper;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

public class AnimationEffect {

	public static class ZoomOutPageTransformer implements PageTransformer {
		private static float MIN_SCALE = 0.85f;

		private static float MIN_ALPHA = 0.5f;

		@Override
		public void transformPage(View view, float position) {
			int pageWidth = view.getWidth();
			int pageHeight = view.getHeight();

			if (position < -1) { // [-Infinity,-1)
									// This page is way off-screen to the left.
				view.setAlpha(0);
				view.setTranslationX(0);
			} else if (position <= 1) { // [-1,1]
										// Modify the default slide transition to
										// shrink the page as well
				float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
				float vertMargin = pageHeight * (1 - scaleFactor) / 2;
				float horzMargin = pageWidth * (1 - scaleFactor) / 2;
				if (position < 0) {
					view.setTranslationX(horzMargin - vertMargin / 2);
				} else {
					view.setTranslationX(-horzMargin + vertMargin / 2);
				}
				// Scale the page down (between MIN_SCALE and 1)
				view.setScaleX(scaleFactor);
				view.setScaleY(scaleFactor);
				// Fade the page relative to its size.
				view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE)
						/ (1 - MIN_SCALE) * (1 - MIN_ALPHA));
			} else { // (1,+Infinity]
						// This page is way off-screen to the right.
				view.setAlpha(0);
				view.setTranslationX(0);
			}
		}
	}
	
    public static class CubeTransformer implements PageTransformer {

		@Override
		public void transformPage(View view, float position) {
			// TODO Auto-generated method stub
		   	/**
	    	 * position����ָ����ҳ���������Ļ���ĵ�λ�á�����һ����̬���ԣ�������ҳ��Ĺ�����ı䡣��һ��ҳ����������Ļ�ǣ����ֵ��0��
	    	 * ��һ��ҳ��ո��뿪��Ļ���ұ�ʱ�����ֵ��1��������Ҳҳ��ֱ������һ��ʱ������һ��ҳ���λ����-0.5����һ��ҳ���λ����0.5��������Ļ��ҳ���λ��
	    	 * ��ͨ��ʹ������setAlpha()��setTranslationX()����setScaleY()����������ҳ������ԣ��������Զ���Ļ���������
	    	 */
			if (position <= 0) {
				//�������󻬶�Ϊ��ǰView
				
				//������ת���ĵ㣻
				ViewHelper.setPivotX(view, view.getMeasuredWidth());
				ViewHelper.setPivotY(view, view.getMeasuredHeight() * 0.5f);
				
				//ֻ��Y������ת����
				ViewHelper.setRotationY(view, 90f * position);
			} else if (position <= 1) {
				//�������һ���Ϊ��ǰView
				ViewHelper.setPivotX(view, 0);
				ViewHelper.setPivotY(view, view.getMeasuredHeight() * 0.5f);
				ViewHelper.setRotationY(view, 90f * position);
			}
		}    	
    } 
    
    public static class DepthPageTransformer implements PageTransformer {
        private final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

    public static class AccordionTransformer implements PageTransformer {

    	@Override
    	public void transformPage(View view, float position) {
    		if (position < -1) {
    			ViewHelper.setPivotX(view, view.getMeasuredWidth() * 0.5f);
    			ViewHelper.setPivotY(view, view.getMeasuredHeight() * 0.5f);
    			ViewHelper.setScaleX(view, 1);
    		} else if (position <= 0) {
    			ViewHelper.setPivotX(view, view.getMeasuredWidth());
    			ViewHelper.setPivotY(view, 0);
    			ViewHelper.setScaleX(view, 1 + position);
    		} else if (position <= 1) {
    			ViewHelper.setPivotX(view, 0);
    			ViewHelper.setPivotY(view, 0);
    			ViewHelper.setScaleX(view, 1 - position);
    		} else {
    			ViewHelper.setPivotX(view, view.getMeasuredWidth() * 0.5f);
    			ViewHelper.setPivotY(view, view.getMeasuredHeight() * 0.5f);
    			ViewHelper.setScaleX(view, 1);
    		}
    	}
    }

    public static class InRightDownTransformer implements PageTransformer {

    	@Override
    	public void transformPage(View view, float position) {
    		int pageHeight = view.getHeight();
    		if (position < -1) {
    			view.setAlpha(1);
    			view.setTranslationY(0);
    		} else if (position <= 0) {
    			view.setTranslationY(pageHeight * position);
    			view.setAlpha(1 + position);

    			// Android 3.1���°汾�������淽����
    			// ViewHelper.setTranslationY(view, pageHeight * -position);
    			// ViewHelper.setAlpha(view, 1 + position);
    		} else if (position <= 1) {
    			view.setTranslationY(view.getHeight() * position);
    			view.setAlpha(1 - position);

    			// Android 3.1���°汾�������淽����
    			// ViewHelper.setTranslationY(view, pageHeight * -position);
    			// ViewHelper.setAlpha(view, 1 - position);
    		} else {
    			view.setTranslationY(0);
    			view.setAlpha(1);
    		}
    	}

    }

    public static class InRightUpTransformer implements PageTransformer {

    	@Override
    	public void transformPage(View view, float position) {
    		int pageHeight = view.getHeight();
    		if (position < -1) {
    			view.setAlpha(1);
    			view.setTranslationY(0);
    		} else if (position <= 0) {
    			view.setTranslationY(pageHeight * -position);
    			view.setAlpha(1 + position);

    			// Android 3.1���°汾�������淽����
    			// ViewHelper.setTranslationY(view, pageHeight * -position);
    			// ViewHelper.setAlpha(view, 1 + position);
    		} else if (position <= 1) {
    			view.setTranslationY(view.getHeight() * -position);
    			view.setAlpha(1 - position);

    			// Android 3.1���°汾�������淽����
    			// ViewHelper.setTranslationY(view, pageHeight * -position);
    			// ViewHelper.setAlpha(view, 1 - position);
    		} else {
    			view.setTranslationY(0);
    			view.setAlpha(1);
    		}
    	}
    }
    
    public static class RotateTransformer implements PageTransformer {

    	/**
    	 * page��Ȼֵ�þ��ǻ����е��Ǹ�view��position������float������ƽʱ����intλ����Ϣ�����ǵ�ǰ����״̬��һ����ʾ��
    	 * ���統��������ȫ��ʱ
    	 * ��position��0�������󻬶���ʹ���ұ߸պ���һ����������Ļʱ��position��1�����ǰһҹ����һҳ�������Ļռһ��ʱ
    	 * ��ǰһҳ��position��-0.5����һҳ��posiotn��0.5�����Ը��position��ֵ���ǾͿ�������������Ҫ��alpha��x/y��Ϣ��
    	 */
    	@Override
    	public void transformPage(View view, float position) {
    		if (position < -1) {
    		} else if (position <= 0) {
    			ViewHelper.setScaleX(view, 1 + position);
    			ViewHelper.setScaleY(view, 1 + position);
    			ViewHelper.setRotation(view, 360 * position);
    		} else if (position <= 1) {
    			ViewHelper.setScaleX(view, 1 - position);
    			ViewHelper.setScaleY(view, 1 - position);
    			ViewHelper.setRotation(view, 360 * position);
    		} else {
    		}
    	}

    }

}
