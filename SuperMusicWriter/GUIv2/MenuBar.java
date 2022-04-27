import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar {

    public MenuBar(MusicEditorUtility meU, JFrame frame) {
        /****************
         * Local Variables
         * Used to set up the menu bar
         ****************/
        JMenuBar menuBar = new JMenuBar();
        JMenu[] menuList = new JMenu[ConstantDataValues.MENUNAME.length];
        JMenuItem[] menu = new JMenuItem[ConstantDataValues.MAXMENUSIZE];
        String[] tempItem = null;
        int[] tempKey = null;

        /*******************************
         * Loop through the menu bar
         *******************************/
        for (int i = 0; i < ConstantDataValues.MENUNAME.length; i++) {
            tempItem = null;
            tempKey = null;

            /*****************************
             * Sets the name of the menubar
             *****************************/
            menuList[i] = new JMenu(ConstantDataValues.MENUNAME[i]);
            /***********************
             * Sets the shortcut key
             ************************/
            menuList[i].setMnemonic(ConstantDataValues.MENUBARSHORTCUT[i]);

            /*********************
             * Defines the menu bar
             *********************/
            if (i == 0) {
                tempItem = ConstantDataValues.FILEITEM;
                tempKey = ConstantDataValues.FILEITEMSHORTCUT;
            } else if (i == 1) {
                tempItem = ConstantDataValues.HELPITEM;
                tempKey = ConstantDataValues.HELPITEMSHORTCUT;

            } else if (i == 2) {
                tempItem = ConstantDataValues.CHANITEM;
                tempKey = ConstantDataValues.CHANSHORTCUT;
            } else if (i == 3) {
                tempItem = ConstantDataValues.PLAYITEM;
                tempKey = ConstantDataValues.PLAYITEMSHORTCUT;
            }

            for (int j = 0; j < tempItem.length; j++) {

                menu[j] = new JMenuItem(tempItem[j]);
                menu[j].addActionListener(meU);
                if (i == 0)
                    menu[j].setIcon(
                            new ImageIcon(getClass().getResource("Icons/" + tempItem[j].toLowerCase() + ".png")));
                menu[j].setMnemonic(tempKey[j]);

                menuList[i].add(menu[j]);

            }
            menuBar.add(menuList[i]);

            frame.setJMenuBar(menuBar);

        }
    }
}