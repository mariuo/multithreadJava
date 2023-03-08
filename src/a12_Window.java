import java.awt.Font;
        import java.util.concurrent.Executors;
        import java.util.concurrent.ScheduledExecutorService;
        import java.util.concurrent.TimeUnit;
        import java.util.function.Supplier;

        import javax.swing.JFrame;
        import javax.swing.JLabel;
        import javax.swing.JPanel;

public final class a12_Window {

    public static synchronized Message createWindow(String textInit) {
        JFrame jFrame = new JFrame(textInit);
        Message jLabel = new Message();
        jLabel.setText("<html><body>" + textInit + "</body></html>");
        jLabel.setFont(new Font("Serif", Font.PLAIN, 72));
        JPanel jPanel = new JPanel();
        jPanel.add(jLabel);
        jFrame.add(jPanel);
        jFrame.setSize(800, 600);
        jFrame.setLocation(1000, 200);
        jFrame.setVisible(true);
        return jLabel;
    }

    public static final void monitore(Supplier<String> supplier) {
        a12_Window.Message msg = a12_Window.createWindow("Contador");
        Runnable monitor = () -> {
            msg.setText(supplier.get());
        };
        ScheduledExecutorService executor =
                Executors.newScheduledThreadPool(1);
        executor.scheduleWithFixedDelay(monitor, 0, 50, TimeUnit.MILLISECONDS);

    }

    public static class Message extends JLabel {
        private static final long serialVersionUID = 1L;
        @Override
        public void setText(String text) {
            super.setText("<html><body><p style=\"width:400px\">" + text + "</p></body></html>");
        }
    }


}