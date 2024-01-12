package top.wcpe.neigeitems.hook

import org.bukkit.Bukkit
import org.bukkit.inventory.ItemStack
import pers.neige.neigeitems.NeigeItems
import top.wcpe.mailbox.entity.Mail

/**
 * 由 WCPE 在 2024/1/12 13:33 创建
 * <p>
 * Created by WCPE on 2024/1/12 13:33
 * <p>
 * <p>
 * GitHub  : <a href="https://github.com/wcpe">wcpe 's GitHub</a>
 * <p>
 * QQ      : 1837019522
 * @author : WCPE
 */
object MailBoxProxy {
    private val mailBoxHook = Bukkit.getPluginManager().getPlugin("MailBox") != null

    @JvmStatic
    fun sendMailBox(
        accepterName: String,
        item: ItemStack
    ): Boolean {
        if (!mailBoxHook) {
            return false
        }
        return try {
            val config = NeigeItems.plugin.config
            val mail = Mail.Builder(
                accepterName,
                config.getString("mail-format.sender") ?: "",
                (config.getString("mail-format.title") ?: "").replace("%player%", accepterName),
                (config.getString("mail-format.message") ?: "").replace("%player%", accepterName),
            ).item(item).build()

            mail.sendMail()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    }
}