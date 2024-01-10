    package Midterm.webgk.Controller;

    import Midterm.webgk.Model.CartItems;

    import Midterm.webgk.Model.Product;
    import Midterm.webgk.Repository.CartItemsRepository;
    import Midterm.webgk.Repository.ProductRepository;
    import Midterm.webgk.Service.CartItemsService;
    import Midterm.webgk.Service.ProductSerivce;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;

    import java.util.*;

    @Controller
    public class CartController {
        @Autowired
        ProductRepository productRepository;
        @Autowired
        CartItemsRepository cartItemsRepository;
        @Autowired
        CartItemsService cartItemsService;
        public final ProductSerivce productService;

        @Autowired

        public CartController(ProductSerivce productService) {
            this.productService = productService;
        }

        @GetMapping("/cart/{userid}")
        public String showCart(Model model, @PathVariable("userid") String userID) {
            List<CartItems> cartItemsList = cartItemsRepository.finduserid(userID);
            List<Product> productList = new ArrayList<>();
            double total = 0;
            List<Integer> quantities = new ArrayList<>();

            for (CartItems cartItems : cartItemsList) {
                Optional<Product> product = productRepository.findById(cartItems.getProductId());
                if (product.isPresent()) {
                    productList.add(product.get());
                    double productPrice = product.get().getPriceProduct();
                    total += productPrice * cartItems.getQuantity();
                    quantities.add(cartItems.getQuantity());
                }
            }

            model.addAttribute("total", total);
            model.addAttribute("user", userID);
            model.addAttribute("product", productList);
            model.addAttribute("quantity", quantities);
            model.addAttribute("totalPriceMessage", total);

            return "cart";
        }

        @GetMapping("/cart/{userid}/add/{productId}")
        public String addToCart(Model model, @PathVariable("productId") Integer productId, @PathVariable("userid") String userID) {
            cartItemsService.updateCartitems(userID, productId, 1);
            return "redirect:/cart/" + userID;
        }
        @PostMapping  ("/cart/{userid}/delete/{productId}")
        public String deleteFromCart(@PathVariable("productId") Integer productId, @PathVariable("userid") String userID) {
            cartItemsRepository.deleteByProductIdAndUserID(productId, userID);
            return "redirect:/cart/" + userID;
        }
        @GetMapping("/cart/{userid}/checkout")
        public String checkout(Model model, @PathVariable("userid") String userID) {
            // Lấy tổng số tiền từ dữ liệu hoặc tính toán nếu cần
            List<CartItems> cartItemsList = cartItemsRepository.finduserid(userID);
            List<Product> productList = new ArrayList<>();
            double total = 0;
            List<Integer> quantities = new ArrayList<>();

            for (CartItems cartItems : cartItemsList) {
                Optional<Product> product = productRepository.findById(cartItems.getProductId());
                if (product.isPresent()) {
                    productList.add(product.get());
                    double productPrice = product.get().getPriceProduct();
                    total += productPrice * cartItems.getQuantity();
                    quantities.add(cartItems.getQuantity());
                }
            }
            String paymentMessage = "Tổng số tiền cần thanh toán: " + total;
            model.addAttribute("paymentMessage", paymentMessage);

            // Xóa tất cả sản phẩm trong giỏ hàng của người dùng
            cartItemsRepository.deleteByProductUserID(userID);

            // Redirect đến trang cart để load lại giỏ hàng mới
            return "redirect:/cart/" + userID;
        }
    }
