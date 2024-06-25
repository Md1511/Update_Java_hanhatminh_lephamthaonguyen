package com.raven.form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerUI extends JFrame {
    protected JButton jButton1;
    protected JScrollPane jScrollPane1;
    protected JTextArea jTextArea1;
    protected JTextField roomID;
    protected ServerSocket serverSocket;

    public ServerUI() {
        setTitle("Server");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        initComponents();
        setLocationRelativeTo(null); // Hiển thị cửa sổ ở giữa màn hình
        setVisible(true);
    }

    private void initComponents() {

        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();
        roomID = new JTextField();
        jButton1 = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("CONTINUE");
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                startServer();
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane1)
                                                .addContainerGap())
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(roomID, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
                                                .addGap(12, 12, 12)
                                                .addComponent(jButton1)
                                                .addGap(0, 498, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(roomID, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton1))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        pack();
    }

    public void start() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept(); // The server accepts connections from clients
                jTextArea1.append("A new client connected to " + socket.getRemoteSocketAddress() + "\n");
                ClientHandler clientHandler = new ClientHandler(socket); // Create a clientHandler to handle the connection from that client
                Thread thread = new Thread(clientHandler); // Every connection is handled in a separate thread
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startServer() {
        String portText = roomID.getText();
        int port = Integer.parseInt(portText);

        // Khởi tạo máy chủ và bắt đầu
        try {
//            jTextArea1.setText("Server has been started on port:" + roomID.getText() + "\n");
            serverSocket = new ServerSocket(port);
            start(); // Gọi phương thức start() của lớp ServerUI hiện tại
            jButton1.setEnabled(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ServerUI();
            }
        });
    }
}