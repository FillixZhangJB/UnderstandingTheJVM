package chapter9.remote_executor;

/**
 * Created by zjb on 2019/7/22.
 * 为了多次载入执行类而加入的加载器
 * 把defineclass方法开放出来，只有外部显示调用的时候才会使用到loadBytes方法
 * 由虚拟机调用时，仍然按照原有的双亲委派机制规则调用loadClass方法进行类加载
 */
public class HotSwapClassLoader extends ClassLoader {
    public HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }

    public Class loadBytes(byte[] classBytes) {
        return defineClass(null, classBytes, 0, classBytes.length);
    }
}
