public class TestMain {

    public static String getNextCode(String lastCode) {
        String CHARS = "abcdefghijklmnopqrstuvwxyz0123456789";

        if (lastCode == null || lastCode.isEmpty()) {
            return "aaaaaa";
        }

        char[] chars = lastCode.toCharArray();

        for (int i = chars.length - 1; i >= 0; i--) {
            int currentIndex = CHARS.indexOf(chars[i]);
            if (currentIndex < CHARS.length() - 1) {
                chars[i] = CHARS.charAt(currentIndex + 1);
                break;
            } else {
                chars[i] = CHARS.charAt(0);
            }
        }

        return new String(chars);
    }
    public static void main(String args[]){
        System.out.println(getNextCode("absui0"));
    }
}
