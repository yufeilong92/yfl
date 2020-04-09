package cn.ruiye.xiaole.utils.subject

/**
 * @Author : YFL  is Creating a porject in xiaole
 * @Package cn.ruiye.xiaole.utils.subject
 * @Email : yufeilong92@163.com
 * @Time :2019/11/13 9:30
 * @Purpose :观察这
 */
class ObjSubject {
    var observers = mutableListOf<ObjObserver>()
    fun attch(o: ObjObserver) {
        if (!observers.contains(o))
            observers.add(o)
    }

    fun notifyAllObservers(vo: Any) {
        if (observers.isNullOrEmpty()) return
        for (item in observers) {
            item.ObjChanger(vo)
        }
    }

    fun unAttch(o: ObjObserver) {
        if (observers.contains(o)) {
            observers.remove(o)
        }
    }
    fun removerAll(){
        observers.clear()
    }
}