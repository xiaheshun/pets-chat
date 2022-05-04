package cn.proxx.chat.pets.message.atapter;

import androidx.annotation.NonNull;

import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;

import cn.proxx.chat.pets.db.table.FriendDO;
import cn.proxx.chat.pets.message.R;

/**
 * @author XUE
 * @since 2019/5/9 10:41
 */
public class FriendAdapter extends BaseRecyclerAdapter<FriendDO> {

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.adapter_friend;
    }

    @Override
    protected void bindData(@NonNull RecyclerViewHolder holder, int position, FriendDO friend) {
        if (null != friend) {
            // 设置内容
            holder.text(R.id.tv_name, friend.getName());
        }
    }
}
