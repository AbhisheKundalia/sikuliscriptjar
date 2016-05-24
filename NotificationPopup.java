package sikulia;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LinearGradientPaint;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class NotificationPopup extends JDialog {
	private final LinearGradientPaint lpg;

	public NotificationPopup() {
		setUndecorated(true);
		setSize(300, 100);

		// size of the screen
		final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		// height of the task bar
		final Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(
				getGraphicsConfiguration());
		final int taskBarSize = scnMax.bottom;

		setLocation(screenSize.width - getWidth()-50, screenSize.height - taskBarSize
				- getHeight()-20);

		// background paint
		lpg = new LinearGradientPaint(0, 0, 0, getHeight() / 2, new float[] { 0f,
				0.2f, .3f }, new Color[] {
						new Color(255, 255, 255, 0),
						new Color(255, 255, 255, 0),
						new Color(255, 255, 255, 0),
		});
		// blue background panel
		setContentPane(new BackgroundPanel());
	}

	private class BackgroundPanel extends JPanel {
		public BackgroundPanel() {
			setOpacity(1);
		}

		@Override
		protected void paintComponent(final Graphics g) {
			final Graphics2D g2d = (Graphics2D) g;
			// background
			g2d.setPaint(lpg);
			g2d.fillRect(1, 1, getWidth() - 2, getHeight() - 2);
			g2d.setColor(Color.RED);

			// border
			float thickness = 5f;
			Stroke oldStroke = g2d.getStroke();
			g2d.setStroke(new BasicStroke(thickness));
			g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
			g2d.setStroke(oldStroke);

		}
	}
	private static final int TIME_VISIBLE = 3000;
	public static void main(final String[] args) throws Exception {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (final Exception e1) {
					e1.printStackTrace();
				}

				final NotificationPopup f = new NotificationPopup();

				final Container c = f.getContentPane();
				c.setLayout(new GridBagLayout());

				final GridBagConstraints constraints = new GridBagConstraints();
				constraints.gridx = 0;
				constraints.gridy = 0;
				constraints.weightx = 1.0f;
				constraints.weighty = 1.0f;
				constraints.insets = new Insets(5, 5, 5, 5);
				constraints.fill = GridBagConstraints.BOTH;

				final JLabel l = new JLabel("<html><div style='text-align: center;'>" + "Pass" + "</html>");
				Font font = l.getFont();
				// same font but bold
				Font boldFont = new Font(Font.SANS_SERIF, Font.BOLD, font.getSize()+12);
				l.setFont(boldFont);
				l.setOpaque(false);
				c.add(l, constraints);

				f.setVisible(true);
				new Timer(TIME_VISIBLE, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						f.dispose();
					}
				}).start();
			}
		});
	}
}