package virtualpet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;

/**
 * This class represents the Swing view of the virtual pet. It implements the VirtualPetView
 * interface. It contains the GUI elements of the virtual pet game. It works with the Swing
 * controller to update the GUI elements and get user input. The GUI elements include the pet's
 * name, age, life stage, mood, health, need levels, and an image of the pet. The GUI also includes
 * buttons for the user to interact with the pet. The GUI is updated when the user interacts with
 * the pet or when the pet's needs decrease over time. The GUI is also updated when the pet grows
 * and enters a new life stage. The GUI is disabled when the pet dies. The GUI provides messages
 * to the user when they interact with the pet or when the pet dies. The pet's image also changes
 * accordingly to the interaction chosen by the user and the life stage of the pet.
 */
public class SwingVirtualPetView extends JFrame implements VirtualPetView {
  /**
   * A list of paths to the icons for the interact buttons and the pet's images.
   */
  private static final List<String> INTERACT_ICONS = List.of("/images/icon_feed.png",
      "/images/icon_play.png", "/images/icon_clean.png", "/images/icon_sleep.png");
  private static final List<String> CHILD_GIFS = List.of("/images/child_normal.gif",
      "/images/eat.gif", "/images/child_play.gif",
      "/images/child_clean.gif", "/images/child_sleep.gif");
  private static final List<String> ADULT_GIFS = List.of("/images/adult_normal.gif",
      "/images/adult_eat.gif", "/images/adult_play.gif",
      "/images/adult_clean.gif", "/images/adult_sleep.gif");

  private static final List<String> SENIOR_GIFS = List.of("/images/senior_normal.gif",
      "/images/senior_eat.gif", "/images/senior_play.gif",
      "/images/senior_clean.gif", "/images/senior_sleep.gif");
  private static final String DEAD_GIF = "/images/dead.gif";

  private final HashMap<String, List<String>> gifs;
  private final JLabel nameDisplay;
  private final JLabel ageDisplay;
  private final JLabel lifeStageDisplay;
  private final JLabel moodDisplay;
  private final JLabel healthDisplay;
  private final JProgressBar hungerDisplay;
  private final JProgressBar socialDisplay;
  private final JProgressBar hygieneDisplay;
  private final JProgressBar energyDisplay;
  private final JButton[] interactButtons;
  private final JLabel petDisplay;
  private final JLabel userDisplay;
  private JOptionPane namePane;

  /**
   * Creates a new Swing view of the virtual pet. It initializes the GUI elements of the virtual
   * pet game.
   *
   * @param caption the caption of the JFrame.
   */
  public SwingVirtualPetView(String caption) {
    super(caption);

    this.gifs = initializeLifeStageGifsHashMap();

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setPreferredSize(new Dimension(500, 300));
    setResizable(false);

    namePane = new JOptionPane();

    JPanel statsPanel = new JPanel(new GridLayout(9, 0));

    nameDisplay = new JLabel("");
    statsPanel.add(nameDisplay);

    ageDisplay = new JLabel("");
    statsPanel.add(ageDisplay);

    lifeStageDisplay = new JLabel("");
    statsPanel.add(lifeStageDisplay);

    moodDisplay = new JLabel("");
    statsPanel.add(moodDisplay);

    healthDisplay = new JLabel("");
    statsPanel.add(healthDisplay);

    hungerDisplay = new JProgressBar(0, 100);
    hungerDisplay.setStringPainted(true);
    statsPanel.add(hungerDisplay);

    socialDisplay = new JProgressBar(0, 100);
    socialDisplay.setStringPainted(true);
    statsPanel.add(socialDisplay);

    hygieneDisplay = new JProgressBar(0, 100);
    hygieneDisplay.setStringPainted(true);
    statsPanel.add(hygieneDisplay);

    energyDisplay = new JProgressBar(0, 100);
    energyDisplay.setStringPainted(true);
    statsPanel.add(energyDisplay);

    this.add(statsPanel, BorderLayout.WEST);

    JSeparator separator = new JSeparator(JSeparator.VERTICAL);

    this.add(separator);

    JPanel petPanel = new JPanel(new BorderLayout());
    petPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

    userDisplay = new JLabel("");
    userDisplay.setHorizontalAlignment(JLabel.CENTER);
    userDisplay.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
    petPanel.add(userDisplay, BorderLayout.PAGE_START);

    petDisplay = new JLabel("");
    petDisplay.setHorizontalAlignment(JLabel.CENTER);
    petDisplay.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 15, 10));
    petDisplay.setPreferredSize(new Dimension(250, 170));
    petPanel.add(petDisplay);

    JPanel interactPanel = new JPanel(new FlowLayout());
    interactButtons = new JButton[4];

    for (int i = 0; i < 4; i++) {
      String pathToIcon = INTERACT_ICONS.get(i);
      Image image = new ImageIcon(getClass().getResource(pathToIcon)).getImage();
      Image scaledImage = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
      ImageIcon scaledIcon = new ImageIcon(scaledImage);
      interactButtons[i] = new JButton(scaledIcon);
      interactButtons[i].setPreferredSize(new Dimension(50, 50));
      interactButtons[i].setMargin(new java.awt.Insets(0, 0, 0, 0));
      interactButtons[i].setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 20));
      interactPanel.add(interactButtons[i]);
    }
    petPanel.add(interactPanel, BorderLayout.PAGE_END);

    this.add(petPanel);

    pack();
    setLocationRelativeTo(null);
  }

  @Override
  public void showGame() {
    setVisible(true);
  }

  @Override
  public String getNameInput() {
    String name = namePane.showInputDialog("Enter a name for your pet:");
    if (name != null) {
      return name;
    // if user presses cancel -- exit out of program
    } else {
      namePane.showMessageDialog(null, "Goodbye.");
      this.dispose();
      System.exit(0);
    }
    return null;
  }

  @Override
  public void displayNameError(String display) {
    namePane.showMessageDialog(null, display);
  }

  @Override
  public void setEventListeners(VirtualPetController controller) {
    for (int i = 0; i < 4; i++) {
      final int finalI = i;
      interactButtons[i].addActionListener(evt -> controller.onActionClick(Need.values()[finalI]));
    }
  }

  @Override
  public void setNameDisplay(String display) {
    nameDisplay.setText(display);
  }

  @Override
  public void setAgeDisplay(String display) {
    ageDisplay.setText(display);
  }

  @Override
  public void setLifeStageDisplay(String display) {
    lifeStageDisplay.setText(display);
  }

  @Override
  public void setMoodDisplay(String display) {
    if (display.contains("Happy")) {
      moodDisplay.setForeground(java.awt.Color.BLACK);
    } else if (display.contains("Sad")) {
      moodDisplay.setForeground(java.awt.Color.BLUE);
    } else {
      moodDisplay.setForeground(java.awt.Color.RED);
    }
    moodDisplay.setText(display);
  }

  @Override
  public void setNeedsLevels(HashMap<Need, Integer> levels) {
    hungerDisplay.setString("Hunger: " + String.valueOf(levels.get(Need.HUNGER)));
    hungerDisplay.setValue(levels.get(Need.HUNGER));

    socialDisplay.setString("Social: " + String.valueOf(levels.get(Need.SOCIAL)));
    socialDisplay.setValue(levels.get(Need.SOCIAL));

    hygieneDisplay.setString("Hygiene: " + String.valueOf(levels.get(Need.HYGIENE)));
    hygieneDisplay.setValue(levels.get(Need.HYGIENE));

    energyDisplay.setString("Energy: " + String.valueOf(levels.get(Need.ENERGY)));
    energyDisplay.setValue(levels.get(Need.ENERGY));
  }

  @Override
  public void setHealthDisplay(String display) {
    if ("Health: Sick".equals(display)) {
      healthDisplay.setForeground(java.awt.Color.RED);
    } else {
      healthDisplay.setForeground(java.awt.Color.BLACK);
    }
    healthDisplay.setText(display);
  }

  @Override
  public void setPetDisplay(Health health, PetLifeStage lifeStage) {
    if (health == Health.DEAD) {
      petDisplay.setIcon(new ImageIcon(getClass().getResource(DEAD_GIF)));
    } else {
      String pathToGif = gifs.get(lifeStage.toString()).get(0);
      petDisplay.setIcon(new ImageIcon(getClass().getResource(pathToGif)));
    }
  }

  @Override
  public void setPetDisplayOnAction(Need need, PetLifeStage lifeStage) {
    List<String> gifsList = gifs.get(lifeStage.toString());
    switch (need) {
      case HUNGER:
        playGif(gifsList.get(1), "1", lifeStage);
        break;
      case SOCIAL:
        playGif(gifsList.get(2), "2", lifeStage);
        break;
      case HYGIENE:
        playGif(gifsList.get(3), "3", lifeStage);
        break;
      case ENERGY:
        playGif(gifsList.get(4), "4", lifeStage);
        break;
      default:
        break;
    }
  }

  @Override
  public void setUserDisplay(String display) {
    switch (display) {
      case "1":
        userDisplay.setText("Eating complete!");
        break;
      case "2":
        userDisplay.setText("Playing complete!");
        break;
      case "3":
        userDisplay.setText("Cleaning complete!");
        break;
      case "4":
        userDisplay.setText("Sleeping complete!");
        break;
      default:
        userDisplay.setText(display);
        break;
    }
  }

  @Override
  public void disableButtons() {
    for (int i = 0; i < 4; i++) {
      interactButtons[i].setEnabled(false);
    }
  }

  /**
   * Enables the interact buttons.
   */
  private void enableButtons() {
    for (int i = 0; i < 4; i++) {
      interactButtons[i].setEnabled(true);
    }
  }

  /**
   * Plays a gif corresponding to the action number of an interaction. A gif is given a 1 second
   * to play and the buttons are disabled to prevent user interaction. After the gif is done
   * playing, the buttons are enabled again and the user display is updated.
   *
   * @param pathToGif    path to the gif to be shown.
   * @param actionNumber an action number, corresponds to one of the interactions.
   *                     1 = feed, 2 = play, 3 = clean, 4 = sleep.
   * @param lifeStage    the pet's life stage, used to determine the gif to show after interaction.
   */
  private void playGif(String pathToGif, String actionNumber, PetLifeStage lifeStage) {
    new Thread(() -> {
      petDisplay.setIcon(new ImageIcon(getClass().getResource(pathToGif)));
      disableButtons();
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      enableButtons();
      String pathToCurrentGif = gifs.get(lifeStage.toString()).get(0);
      SwingUtilities.invokeLater(() -> {
        petDisplay.setIcon(new ImageIcon(getClass().getResource(pathToCurrentGif)));
      });
      setUserDisplay(actionNumber);
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
  }

  /**
   * Initializes a HashMap with the life stages as keys and a list of paths to the gifs as values.
   *
   * @return a HashMap with the life stages as keys and a list of paths to the gifs as values.
   */
  private HashMap<String, List<String>> initializeLifeStageGifsHashMap() {
    HashMap<String, List<String>> gifs = new HashMap<>();
    gifs.put("Child", CHILD_GIFS);
    gifs.put("Adult", ADULT_GIFS);
    gifs.put("Senior", SENIOR_GIFS);
    return gifs;
  }
}
