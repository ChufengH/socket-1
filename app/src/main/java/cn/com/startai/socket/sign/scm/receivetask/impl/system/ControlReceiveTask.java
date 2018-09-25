package cn.com.startai.socket.sign.scm.receivetask.impl.system;

import cn.com.startai.socket.sign.scm.receivetask.OnTaskCallBack;
import cn.com.swain.support.protocolEngine.datagram.SocketDataArray;
import cn.com.swain.support.protocolEngine.task.SocketResponseTask;
import cn.com.swain.support.protocolEngine.utils.SocketSecureKey;
import cn.com.swain169.log.Tlog;

/**
 * author: Guoqiang_Sun
 * date : 2018/4/26 0026
 * desc :
 */
public class ControlReceiveTask extends SocketResponseTask {

    private OnTaskCallBack mTaskCallBack;

    public ControlReceiveTask(OnTaskCallBack mTaskCallBack) {
        Tlog.e(TAG, " new ControlReceiveTask() ");
        this.mTaskCallBack = mTaskCallBack;
    }

    @Override
    protected void doTask(SocketDataArray mSocketDataArray) {

        byte[] protocolParams = mSocketDataArray.getProtocolParams();

        if (protocolParams == null || protocolParams.length < 1) {
            Tlog.e(TAG, " ControlReceiveTask error:" + mSocketDataArray.toString());
            if (mTaskCallBack != null) {
                mTaskCallBack.onConnectResult(false, mSocketDataArray.getID());
            }
            return;
        }

        boolean result = SocketSecureKey.Util.resultIsOk(protocolParams[0]);

        Tlog.e(TAG, " ControlReceiveTask result:" + result + " params:" + result);

        if (mTaskCallBack != null) {
            mTaskCallBack.onConnectResult(result, mSocketDataArray.getID());
        }

    }
}