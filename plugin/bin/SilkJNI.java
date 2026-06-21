public class SilkJNI {
    private static boolean loaded = false;
    public static synchronized String loadNative(String soPath) {
        if (loaded) return null;
        try {
            System.load(soPath);
            loaded = true;
            return null;
        } catch (Throwable t) {
            return t.getClass().getSimpleName() + ": " + t.getMessage();
        }
    }
    public static native int encode(String pcmPath, String silkPath, int sampleRate);
}
