import React, { useState } from 'react';
import PropTypes from 'prop-types'; 
import axios from 'axios';
import registerImage from '../../assets/imgs/loginImage.jpg';
import svglogin from '../../assets/imgs/login-svgrepo-com.svg';

interface RegisterFormProps {
    onRegister: () => void;
}

const RegisterForm: React.FC<RegisterFormProps> = ({ onRegister }) => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmedPassword, setConfirmedPassword] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/api/v1/admins', {
                email,
                passwordForm : {
                    password,
                    confirmedPassword
                }
            });
            onRegister();
        } catch (error) {
            setError('Registration failed');
        }
    };

    return (
        <div className="flex justify-center h-screen">
            <div className="hidden bg-cover lg:block lg:w-2/3" style={{ backgroundImage: `url(${registerImage})` }}>
                <div className="flex-1">
                    <div className="text-center mb-8 mx-auto" style={{ width: '100px' }}>
                        <img src={svglogin} alt="login" className='w-20' />
                    </div>
                    <form onSubmit={handleSubmit}>
                        {error && <div className="mb-4 text-red-500">{error}</div>}
                        <div className="mb-6">
                            <label htmlFor="email"
                                   className="block mb-2 text-sm text-gray-600 dark:text-gray-200">Email</label>
                            <input
                                type="email"
                                id="email"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                                placeholder="Your Email"
                                className="block w-full px-4 py-2 mt-2 text-gray-700 placeholder-gray-400 bg-white border border-gray-200 rounded-lg dark:placeholder-gray-600 dark:bg-gray-900 dark:text-gray-300 dark:border-gray-700 focus:border-blue-400 dark:focus:border-blue-400 focus:ring-blue-400 focus:outline-none focus:ring focus:ring-opacity-40"
                                required
                            />
                        </div>
                        <div className="mb-4">
                            <label htmlFor="password"
                                   className="block mb-2 text-sm text-gray-600 dark:text-gray-200">Password</label>
                            <input
                                type="password"
                                id="password"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                placeholder="Password"
                                className="block w-full px-4 py-2 mt-2 text-gray-700 placeholder-gray-400 bg-white border border-gray-200 rounded-lg dark:placeholder-gray-600 dark:bg-gray-900 dark:text-gray-300 dark:border-gray-700 focus:border-blue-400 dark:focus:border-blue-400 focus:ring-blue-400 focus:outline-none focus:ring focus:ring-opacity-40"
                                required
                            />
                        </div>
                        <div className="mb-4">
                            <label htmlFor="confirmedPassword"
                                   className="block mb-2 text-sm text-gray-600 dark:text-gray-200">Confirmed Password</label>
                            <input
                                type="password"
                                id="confirmedPassword"
                                value={confirmedPassword}
                                onChange={(e) => setConfirmedPassword(e.target.value)}
                                placeholder="Password"
                                className="block w-full px-4 py-2 mt-2 text-gray-700 placeholder-gray-400 bg-white border border-gray-200 rounded-lg dark:placeholder-gray-600 dark:bg-gray-900 dark:text-gray-300 dark:border-gray-700 focus:border-blue-400 dark:focus:border-blue-400 focus:ring-blue-400 focus:outline-none focus:ring focus:ring-opacity-40"
                                required
                            />
                        </div>
                        <div className="mt-6">
                            <button type="submit"
                                    className="w-full px-4 py-2 tracking-wide text-white transition-colors duration-300 transform bg-green-500 rounded-lg hover:bg-green-400 focus:outline-none focus:bg-green-400 focus:ring focus:ring-green-300 focus:ring-opacity-50">
                                Add Admin
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
};

RegisterForm.propTypes = {
    onRegister: PropTypes.func.isRequired
};

export default RegisterForm;
