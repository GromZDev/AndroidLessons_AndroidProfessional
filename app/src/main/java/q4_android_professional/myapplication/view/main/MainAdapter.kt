package q4_android_professional.myapplication.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import myapplication.model.data.data.DataModel
import myapplication.repository.convertMeaningsToString
import q4_android_professional.myapplication.databinding.ItemMainFragmentRvBinding
import q4_android_professional.myapplication.utils.ImageLoader

class MainAdapter(
    private var onListItemClickListener: OnListItemClickListener,
    private var data: List<DataModel>,
    val imageLoader: ImageLoader<ShapeableImageView>
) :
    RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {


    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RecyclerItemViewHolder(
            ItemMainFragmentRvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(private val vb: ItemMainFragmentRvBinding) :
        RecyclerView.ViewHolder(vb.root) {


        fun bind(data: DataModel) = with(vb) {
            if (layoutPosition != RecyclerView.NO_POSITION) {

                headerTextviewRecyclerItem.text = data.text

                descriptionTextviewRecyclerItem.text =
                    data.meanings[0].translatedMeaning.translatedMeaning

                imageLoader.loadInto(
                    "https:" + data.meanings[0].previewUrlNew, itemWordImage
                )

                transcriptionTextviewRecyclerItem.text = convertMeaningsToString(
                    data.meanings
                )

                itemView.setOnClickListener { openInNewWindow(data) }
            }
        }
    }

    private fun openInNewWindow(listItemData: DataModel) {
        onListItemClickListener.onItemClick(listItemData)
    }

    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
    }

}