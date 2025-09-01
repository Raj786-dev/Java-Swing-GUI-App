import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ProductOrderingApp {
    private JFrame frame;
    private JTextField searchField;
    private DefaultListModel<String> productListModel;
    private DefaultListModel<String> cartListModel;
    private JList<String> productList;
    private JList<String> cartList;
    private ArrayList<String> products;

    public ProductOrderingApp() {
        // Product data
        products = new ArrayList<>();
        products.add("Laptop");
        products.add("Smartphone");
        products.add("Headphones");
        products.add("Smartwatch");
        products.add("Tablet");
        products.add("Camera");

        // Frame setup
        frame = new JFrame("Product Ordering App");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Top: Search bar
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Search:"));
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        topPanel.add(searchField);
        topPanel.add(searchButton);
        frame.add(topPanel, BorderLayout.NORTH);

        // Left: Product list
        productListModel = new DefaultListModel<>();
        for (String p : products) productListModel.addElement(p);
        productList = new JList<>(productListModel);
        JScrollPane productScroll = new JScrollPane(productList);
        productScroll.setBorder(BorderFactory.createTitledBorder("Products"));

        // Right: Cart list
        cartListModel = new DefaultListModel<>();
        cartList = new JList<>(cartListModel);
        JScrollPane cartScroll = new JScrollPane(cartList);
        cartScroll.setBorder(BorderFactory.createTitledBorder("Cart"));

        // Center panel (Products + Cart side by side)
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.add(productScroll);
        centerPanel.add(cartScroll);
        frame.add(centerPanel, BorderLayout.CENTER);

        // Bottom: Buttons
        JPanel bottomPanel = new JPanel();
        JButton addBtn = new JButton("Add to Cart");
        JButton removeBtn = new JButton("Remove from Cart");
        JButton checkoutBtn = new JButton("Checkout");
        bottomPanel.add(addBtn);
        bottomPanel.add(removeBtn);
        bottomPanel.add(checkoutBtn);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Action Listeners
        searchButton.addActionListener(e -> searchProducts());
        addBtn.addActionListener(e -> addToCart());
        removeBtn.addActionListener(e -> removeFromCart());
        checkoutBtn.addActionListener(e -> checkout());

        frame.setVisible(true);
    }

    private void searchProducts() {
        String query = searchField.getText().toLowerCase();
        productListModel.clear();
        for (String p : products) {
            if (p.toLowerCase().contains(query)) {
                productListModel.addElement(p);
            }
        }
    }

    private void addToCart() {
        String selected = productList.getSelectedValue();
        if (selected != null) {
            cartListModel.addElement(selected);
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a product!");
        }
    }

    private void removeFromCart() {
        String selected = cartList.getSelectedValue();
        if (selected != null) {
            cartListModel.removeElement(selected);
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a product from cart!");
        }
    }

    private void checkout() {
        if (cartListModel.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Cart is empty!");
        } else {
            JOptionPane.showMessageDialog(frame, "Order placed for: " + cartListModel.toString());
            cartListModel.clear();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProductOrderingApp::new);
    }
}
