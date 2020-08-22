package com.backpacker.yflLibrary.view.swipe

/**
 * @Author : YFL  is Creating a porject in My Application
 * @Package com.backpacker.yflLibrary.view.swipe
 * @Email : yufeilong92@163.com
 * @Time :2020/8/22 16:57
 * @Purpose :数据
 */
/*<?xml version="1.0" encoding="utf-8"?>
<com.blankj.swipepanel.SwipePanel
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
xmlns:app="http://schemas.android.com/apk/res-auto"
android:id="@+id/swipePanel"
android:background="@color/mediumGray"
android:layout_width="match_parent"
android:layout_height="match_parent"
tools:context=".LayoutSwipePanelActivity"
app:isLeftCenter="false"
app:leftEdgeSize="100dp"
app:leftSwipeColor="@color/colorPrimary"
app:leftDrawable="@drawable/base_back"
app:isTopCenter="true"
app:topEdgeSize="100dp"
app:topSwipeColor="@color/colorAccent"
app:topDrawable="@drawable/ic_rotate"
app:isRightCenter="false"
app:rightEdgeSize="100dp"
app:rightSwipeColor="@color/colorPrimary"
app:rightDrawable="@drawable/base_back"
app:isBottomCenter="true"
app:bottomEdgeSize="100dp"
app:bottomSwipeColor="@color/colorAccent"
app:bottomDrawable="@mipmap/ic_launcher_round">

<androidx.constraintlayout.widget.ConstraintLayout
android:layout_width="match_parent"
android:layout_height="match_parent">

</androidx.constraintlayout.widget.ConstraintLayout>

</com.blankj.swipepanel.SwipePanel>

 使用方法

        final SwipePanel swipePanel = findViewById(R.id.swipePanel);
        swipePanel.setOnFullSwipeListener(new SwipePanel.OnFullSwipeListener() {
            @Override
            public void onFullSwipe(int direction) {
               Toast.makeText(LayoutSwipePanelActivity.this, "onFullSwipe"+direction, Toast.LENGTH_SHORT).show();
                if (direction == SwipePanel.TOP) {
                    swipePanel.close(true);
                }
            }

        });
        swipePanel.setOnProgressChangedListener(new SwipePanel.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(int direction, float progress, boolean isTouch) {
                if (direction == SwipePanel.TOP) {
                    Toast.makeText(LayoutSwipePanelActivity.this, "onProgressChanged"+progress, Toast.LENGTH_SHORT).show();
                    RotateDrawable drawable = (RotateDrawable) swipePanel.getTopDrawable();
                    drawable.setLevel((int) (progress * 20000));
                }
            }
        });

        不适用xml
                final SwipePanel swipePanel = new SwipePanel(this);
        swipePanel.setLeftEdgeSize(100);// 设置左侧触发阈值 100dp
        swipePanel.setLeftDrawable(R.drawable.base_back);// 设置左侧 icon
        swipePanel.wrapView(findViewById(R.id.rootLayout));// 设置嵌套在 rootLayout 外层
        swipePanel.setOnFullSwipeListener(true,new SwipePanel.OnFullSwipeListener() {// 设置完全划开松手后的监听
            @Override
            public void onFullSwipe(int direction) {
//                swipePanel.close(true);// 关闭
                finish();
            }
        });
        swipePanel.setOnProgressChangedListener(new SwipePanel.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(int direction, float progress, boolean isTouch) {
                Log.e("==", "onProgressChanged" + progress);
            }
        });
*/
