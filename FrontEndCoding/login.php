<?php
$username = $_POST['username'];
$password = $_POST['password'];

// Add your login logic here

if ($username == 'admin' && $password == 'password') {
echo 'Login successful!';
} else {
echo 'Invalid username or password.';
}
?>