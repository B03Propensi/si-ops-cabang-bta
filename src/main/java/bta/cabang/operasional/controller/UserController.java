package bta.cabang.operasional.controller;

@RestController
public class UserController {
    @Autowired
    UserService service;

    @PostMapping(value = "/login")
    public BaseResponse Login(@RequestBody LoginDetails login) {
        try{
            UserModel user = service.getUserByNama(login.getUsername());
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if(encoder.matches(login.getPassword(), user.getPassword())){
                return new BaseResponse(200, "Login Berhasil", user);
            } else {
                return new BaseResponse(403, "Login Gagal", login);
            }
        } catch(Exception e) {
            return new BaseResponse(404, "Akun tidak ditemukan", null);
        }
    }

    @PostMapping(value = "/addUser")
    public ResponseEntity<String> addUser(@RequestBody UserInput user) {
        UserModel newUser = new UserModel();
        newUser.setAlamat(user.getAlamat());
        newUser.setEmail(user.getEmail());
        newUser.setNamaUser(user.getNamaUser());
        newUser.setNip(user.getNip());
        newUser.setPassword(user.getPassword());
        newUser.setUsername(user.getUsername());
        newUser.setRole(service.getRole(user.getRole()));
        return ResponseEntity.ok("User berhasil ditambahkan");
    }

    @GetMapping(value = "/allRole")
    public List<RoleModel> getAllRole(){
        return service.getAllRole();
    }

    @PostMapping(value = "/addRole")
    public ResponseEntity<String> addRole(@RequestBody RoleModel role) {
        service.addRole(role);
        return ResponseEntity.ok("Role berhasil ditambahkan");
    }
}
