package com.fan.mvvm.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fan.mvvm.R

class LifecycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)


        // 这里, MyPresenter 是观察者, 观察 Activity 的变化.
        // 当 Activity 的状态发生变化, MyPresenter 能够得到通知.
        getLifecycle().addObserver(MyPresenter()) //添加LifecycleObserver

        // 注册后, LifecycleObserver(MyPresenter) 便可以观察到LifecycleOwner(Activity)的生命周期事件

        // LifecycleObserver接口（ Lifecycle观察者）：实现该接口的类，通过注解的方式，可以通过被LifecycleOwner类的addObserver(LifecycleObserver o)方法注册,被注册后，LifecycleObserver便可以观察到LifecycleOwner的生命周期事件。
        //
        // LifecycleOwner接口（Lifecycle持有者）：实现该接口的类持有生命周期(Lifecycle对象)，该接口的生命周期(Lifecycle对象)的改变会被其注册的观察者LifecycleObserver观察到并触发其对应的事件。
        //
        // Lifecycle(生命周期)：和LifecycleOwner不同的是，LifecycleOwner本身持有Lifecycle对象，LifecycleOwner通过其Lifecycle getLifecycle()的接口获取内部Lifecycle对象。
        //
        // State(当前生命周期所处状态)：如图所示。
        //
        // Event(当前生命周期改变对应的事件)：如图所示，当Lifecycle发生改变，如进入onCreate,会自动发出ON_CREATE事件。
        //————————————————
        //版权声明：本文为CSDN博主「却把清梅嗅」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
        //原文链接：https://blog.csdn.net/mq2553299/article/details/79029657


        // Lifecycle 对象就是: LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);


        // 持有Lifecycle有什么作用呢？实际上在Fragment对应的生命周期内，都会发送对应的生命周期事件给内部的 LifecycleRegistry对象处理：
        //
        //public class Fragment implements xxx, LifecycleOwner {
        //    //...
        //    void performCreate(Bundle savedInstanceState) {
        //        onCreate(savedInstanceState);  //1.先执行生命周期方法
        //        //...省略代码
        //        //2.生命周期事件分发
        //        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        //    }
        //
        //    void performStart() {
        //        onStart();
        //        //...
        //        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
        //    }
        //
        //    void performResume() {
        //         onResume();
        //        //...
        //        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        //    }
        //
        //    void performPause() {
        //        //3.注意，调用顺序变了
        //        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
        //        //...
        //        onPause();
        //    }
        //
        //    void performStop() {
        //       mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
        //        //...
        //        onStop();
        //    }
        //
        //    void performDestroy() {
        //        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
        //        //...
        //        onDestroy();
        //    }
        //}
        //————————————————
        //版权声明：本文为CSDN博主「却把清梅嗅」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
        //原文链接：https://blog.csdn.net/mq2553299/article/details/79029657


        // 当Fragment将生命周期对应的事件交给其内部的Lifecycle处理后， LifecycleObserver （就是我们上文自定义的Presenter），就能够接收到对应的生命周期事件，这是如何实现的呢？

        // 我们看一下LifecycleRegistry 的handleLifecycleEvent()方法：
        //
        //        /**
        //     * Sets the current state and notifies the observers.
        //     * <p>
        //     * Note that if the {@code currentState} is the same state as the last call to this method,
        //     * calling this method has no effect.
        //     *
        //     * @param event The event that was received
        //     */
        //    public void handleLifecycleEvent(@NonNull Lifecycle.Event event) {
        //        enforceMainThreadIfNeeded("handleLifecycleEvent");
        //        moveToState(event.getTargetState());
        //    }
        //1
        //2
        //3
        //4
        //看方法的名字我们就可以知道，handleLifecycleEvent方法会通过 getStateAfter 获取当前应处的状态并修改 Lifecycle本身的State 值，紧接着遍历所 LifecycleObserver 并同步且通知其状态发生变化，因此就能触发LifecycleObserver 对应的生命周期事件。
        //————————————————
        //版权声明：本文为CSDN博主「却把清梅嗅」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
        //原文链接：https://blog.csdn.net/mq2553299/article/details/79029657


        //     private void moveToState(State next) {
        //        if (mState == next) {
        //            return;
        //        }
        //        mState = next;
        //        if (mHandlingEvent || mAddingObserverCounter != 0) {
        //            mNewEventOccurred = true;
        //            // we will figure out what to do on upper level.
        //            return;
        //        }
        //        mHandlingEvent = true;
        //        sync();
        //        mHandlingEvent = false;
        //    }


        //     // happens only on the top of stack (never in reentrance),
        //    // so it doesn't have to take in account parents
        //    private void sync() {
        //        LifecycleOwner lifecycleOwner = mLifecycleOwner.get();
        //        if (lifecycleOwner == null) {
        //            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is already"
        //                    + "garbage collected. It is too late to change lifecycle state.");
        //        }
        //        while (!isSynced()) {
        //            mNewEventOccurred = false;
        //            // no need to check eldest for nullability, because isSynced does it for us.
        //            if (mState.compareTo(mObserverMap.eldest().getValue().mState) < 0) {
        //                backwardPass(lifecycleOwner);
        //            }
        //            Map.Entry<LifecycleObserver, ObserverWithState> newest = mObserverMap.newest();
        //            if (!mNewEventOccurred && newest != null
        //                    && mState.compareTo(newest.getValue().mState) > 0) {
        //                forwardPass(lifecycleOwner);
        //            }
        //        }
        //        mNewEventOccurred = false;
        //    }


        //        private void forwardPass(LifecycleOwner lifecycleOwner) {
        //            Iterator<Map.Entry<LifecycleObserver, ObserverWithState>> ascendingIterator =
        //            mObserverMap.iteratorWithAdditions();
        //            while (ascendingIterator.hasNext() && !mNewEventOccurred) {
        //                Map.Entry<LifecycleObserver, ObserverWithState> entry = ascendingIterator.next();
        //                ObserverWithState observer = entry.getValue();
        //                while ((observer.mState.compareTo(mState) < 0 && !mNewEventOccurred
        //                            && mObserverMap.contains(entry.getKey()))) {
        //                    pushParentState(observer.mState);
        //                    final Event event = Event.upFrom(observer.mState);
        //                    if (event == null) {
        //                        throw new IllegalStateException("no event up from " + observer.mState);
        //                    }
        //                    observer.dispatchEvent(lifecycleOwner, event);
        //                    popParentState();
        //                }
        //            }
        //        }


        // 遍历, 然后将事件派发给了观察者  observer.dispatchEvent(lifecycleOwner, event);


    }
}
