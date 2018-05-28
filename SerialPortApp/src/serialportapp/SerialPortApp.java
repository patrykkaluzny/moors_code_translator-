package serialportapp;

import java.util.ArrayList;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortList;
import jssc.SerialPortException;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.nio.charset.Charset;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import javax.swing.UIManager;

public class SerialPortApp extends javax.swing.JFrame {

    private static SerialPort serialPort;
    private static String mPortName;
    private static int mBaudRate, mDataBits, mStopBits, mParity;
    private static Timer timer;
    private static ArrayList<String> toSend;
    private static char bytestart = 36;
    private static char bytestop = 37;
    private static String lastMessage;
    private static boolean saving = false;

    public SerialPortApp() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {

            ex.printStackTrace();
        }

        initComponents();
        receiveText.setText(null);
        toSend = new ArrayList();
        receiveText.setEditable(false);
        disconnectButton.setEnabled(false);
        sendButton.setEnabled(false);
        this.setLocationRelativeTo(null);
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimerFunction();
            }
        });
        setSettings();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        connectButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        portBox = new javax.swing.JComboBox<>();
        baudrateBox = new javax.swing.JComboBox<>();
        databitBox = new javax.swing.JComboBox<>();
        parityBox = new javax.swing.JComboBox<>();
        stopBox = new javax.swing.JComboBox<>();
        disconnectButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        sendTextField = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        clearButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        receiveText = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Serial Port App");
        setResizable(false);
        setSize(new java.awt.Dimension(400, 600));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ustawienia portu szeregowego", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12))); // NOI18N
        jPanel2.setToolTipText("");
        jPanel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Nazwa portu:");

        connectButton.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        connectButton.setText("Połącz");
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Baud Rate:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Bity danych:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Parzystość");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Bity stopu:");

        portBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        portBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        baudrateBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        baudrateBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        databitBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        databitBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        parityBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        parityBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        stopBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        stopBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        disconnectButton.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        disconnectButton.setText("Rozłącz");
        disconnectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnectButtonActionPerformed(evt);
            }
        });

        refreshButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/serialportapp/Refresh_32px.png"))); // NOI18N
        refreshButton.setToolTipText("");
        refreshButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refreshButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(connectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(disconnectButton, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                    .addComponent(baudrateBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(databitBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(parityBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stopBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(portBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refreshButton)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(refreshButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(portBox, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(baudrateBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(databitBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(parityBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stopBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(connectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(disconnectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(134, 134, 134))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Odebrane"), "Przetłumacz (maksymalnie 10 liter)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12))); // NOI18N
        jPanel4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        sendTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        sendTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendTextFieldActionPerformed(evt);
            }
        });

        sendButton.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        sendButton.setText("Tłumacz");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sendTextField)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(sendTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Przetłumaczone wyrazy", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12))); // NOI18N

        clearButton.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        clearButton.setText("Wyczyść");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        receiveText.setColumns(20);
        receiveText.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        receiveText.setRows(5);
        jScrollPane1.setViewportView(receiveText);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        getPortSettings();
        connectButton.setEnabled(false);
        portBox.setEnabled(false);
        databitBox.setEnabled(false);
        parityBox.setEnabled(false);
        stopBox.setEnabled(false);
        baudrateBox.setEnabled(false);
        disconnectButton.setEnabled(true);
        sendButton.setEnabled(true);
        receiveText.setText(null);
        setSerialPort();
        toSend.clear();
        timer.start();

    }//GEN-LAST:event_connectButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        receiveText.setText(null);

    }//GEN-LAST:event_clearButtonActionPerformed

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        String tekst = sendTextField.getText().toString().toLowerCase();
        boolean polishLetters = false;
        if (tekst.length() != 0) {
            if (tekst.length() > 10) {
                JOptionPane.showMessageDialog(this, "Wpisano wiecej niz 10 znaków!", "", JOptionPane.PLAIN_MESSAGE);
            } else {
                if (!tekst.chars().allMatch(Character::isLetter)) {
                    JOptionPane.showMessageDialog(this, "Wpisz litery!", "", JOptionPane.PLAIN_MESSAGE);
                } else {
                    for (int i = 0; i < tekst.length(); i++) {
                        if (tekst.charAt(i) == 'ł' || tekst.charAt(i) == 'ą' || tekst.charAt(i) == 'ę' || tekst.charAt(i) == 'ś' || tekst.charAt(i) == 'ć' || tekst.charAt(i) == 'ó' || tekst.charAt(i) == 'ź' || tekst.charAt(i) == 'ż' || tekst.charAt(i) == 'ń') {
                            polishLetters = true;
                            break;
                        }
                    }
                    if (!polishLetters) {
                        toSend.add(bytestart + tekst + bytestop);
                        sendTextField.setText(null);
                        sendButton.setEnabled(false);
                        lastMessage = tekst;
                    } else {
                        JOptionPane.showMessageDialog(this, "Nie używaj polskich znaków!", "", JOptionPane.PLAIN_MESSAGE);
                    }

                }
            }

        }
    }//GEN-LAST:event_sendButtonActionPerformed

    private void sendTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sendTextFieldActionPerformed

    private void disconnectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnectButtonActionPerformed
        disconnnectButton();
        closeSerialPort();
    }//GEN-LAST:event_disconnectButtonActionPerformed

    private void refreshButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshButtonMouseClicked
        String[] portNames = SerialPortList.getPortNames();
        DefaultComboBoxModel portModel = new DefaultComboBoxModel(portNames);
        portBox.setModel(portModel);
        portBox.setSelectedIndex(0);
    }//GEN-LAST:event_refreshButtonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SerialPortApp().setVisible(true);
            }
        });
    }

    private void setSettings() {
        String[] baudRates = {"4800", "9600", "19200", "38400", "57600", "115200"};
        String[] dataBits = {"5", "6", "7", "8"};
        String[] parity = {"None", "Odd", "Even", "Mark", "Space"};
        String[] stopBits = {"1", "2", "1.5"};
        String[] portNames = SerialPortList.getPortNames();

        DefaultComboBoxModel baudModel = new DefaultComboBoxModel(baudRates);
        DefaultComboBoxModel dataModel = new DefaultComboBoxModel(dataBits);
        DefaultComboBoxModel parityModel = new DefaultComboBoxModel(parity);
        DefaultComboBoxModel stopModel = new DefaultComboBoxModel(stopBits);
        DefaultComboBoxModel portModel = new DefaultComboBoxModel(portNames);

        baudrateBox.setModel(baudModel);
        baudrateBox.setSelectedIndex(2);
        portBox.setModel(portModel);
        portBox.setSelectedIndex(0);
        databitBox.setModel(dataModel);
        databitBox.setSelectedIndex(3);
        parityBox.setModel(parityModel);
        parityBox.setSelectedIndex(1);
        stopBox.setModel(stopModel);
        stopBox.setSelectedIndex(0);
    }

    private void TimerFunction() {
        if (toSend.size() > 0) {
            try {
                serialPort.writeBytes(toSend.get(0).getBytes());
                toSend.remove(0);
            } catch (SerialPortException ex) {
                System.out.println(ex);
            }
        }
    }

    private void getPortSettings() {
        mPortName = portBox.getSelectedItem().toString();
        mDataBits = Integer.parseInt(databitBox.getSelectedItem().toString());
        mBaudRate = Integer.parseInt(baudrateBox.getSelectedItem().toString());

        switch (stopBox.getSelectedItem().toString()) {
            case "One":
                mStopBits = SerialPort.STOPBITS_1;
                break;
            case "Two":
                mStopBits = serialPort.STOPBITS_2;
                break;
            case "OnePointFive":
                mStopBits = serialPort.STOPBITS_1_5;
                break;
        }
        switch (parityBox.getSelectedItem().toString()) {
            case "None":
                mParity = serialPort.PARITY_NONE;
                break;
            case "Odd":
                mParity = serialPort.PARITY_ODD;
                break;
            case "Even":
                mParity = serialPort.PARITY_EVEN;
                break;
            case "Mark":
                mParity = serialPort.PARITY_MARK;
                break;
            case "Space":
                mParity = serialPort.PARITY_SPACE;
                break;
        }
    }

    private void setSerialPort() {
        serialPort = new SerialPort(mPortName);
        try {
            serialPort.openPort();//Open port
            serialPort.setParams(mBaudRate, mDataBits, mStopBits, mParity, false, false);//Set params
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
            int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;//Prepare mask
            serialPort.setEventsMask(mask);//Set mask
            serialPort.setRTS(false);
            serialPort.addEventListener(new SerialPortApp.SerialPortReader());//Add SerialPortEventListener
        } catch (SerialPortException ex) {

            JOptionPane.showMessageDialog(this, "Port zajęty, wybierz inny", "", JOptionPane.PLAIN_MESSAGE);
            disconnnectButton();
            System.out.println(ex);
        }
    }

    private void closeSerialPort() {
        try {
            serialPort.closePort();//Open port

        } catch (SerialPortException ex) {

            System.out.println(ex);
        }
    }

    class SerialPortReader implements SerialPortEventListener {

        public void serialEvent(SerialPortEvent event) {
            if (event.isRXCHAR()) {//If data is available
                if (event.getEventValue() > 0) { //Check bytes count in the input buffer
                    String pom = "";
                    for (int i = 0; i < event.getEventValue(); i++) {
                        try {

                            byte buffer[] = serialPort.readBytes(1);
                            try {
                                pom = new String(buffer, "UTF-8");
                            } catch (Exception ex) {

                                System.out.println(ex);
                            }
                            if (pom.equals("$")) {
                                if (!saving) {
                                    receiveText.setText("Tłumaczony wyraz: " + lastMessage + "\n" + "Kod Morse'a: ");
                                    saving = true;
                                    sendButton.setEnabled(true);
                                }

                            } else if (pom.equals("%")) {
                                if (saving) {
                                    saving = false;
                                }

                            } else {
                                if (saving) {
                                    receiveText.setText(receiveText.getText().toString() + pom);
                                }
                            }

                        } catch (SerialPortException ex) {

                            System.out.println(ex);
                        }
                    }
                }
            } else if (event.isCTS()) {//If CTS line has changed state
                if (event.getEventValue() == 1) {//If line is ON

                    System.out.println("CTS - ON");
                } else {

                    System.out.println("CTS - OFF");
                }
            } else if (event.isDSR()) {///If DSR line has changed state
                if (event.getEventValue() == 1) {//If line is ON
                    System.out.println("DSR - ON");

                } else {

                    System.out.println("DSR - OFF");
                }
            }
        }
    }

    private void disconnnectButton() {
        connectButton.setEnabled(true);
        portBox.setEnabled(true);
        databitBox.setEnabled(true);
        parityBox.setEnabled(true);
        stopBox.setEnabled(true);
        baudrateBox.setEnabled(true);
        disconnectButton.setEnabled(false);
        sendButton.setEnabled(false);
        timer.stop();

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> baudrateBox;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton connectButton;
    private javax.swing.JComboBox<String> databitBox;
    private javax.swing.JButton disconnectButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> parityBox;
    private javax.swing.JComboBox<String> portBox;
    private javax.swing.JTextArea receiveText;
    private javax.swing.JLabel refreshButton;
    private javax.swing.JButton sendButton;
    private javax.swing.JTextField sendTextField;
    private javax.swing.JComboBox<String> stopBox;
    // End of variables declaration//GEN-END:variables
}
