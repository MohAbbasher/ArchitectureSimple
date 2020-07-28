package example.architecturesimple.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import example.architecturesimple.data.User
import example.architecturesimple.databinding.UsersListItemBinding

class UserAdapter:
    ListAdapter<User, UserAdapter.ViewHolder>(UserDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val category = getItem(position)
        holder.bind(category)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: UsersListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: User) {
            binding.apply {
                user = item
                executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = UsersListItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}