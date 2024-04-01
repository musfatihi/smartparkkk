import React, { useState } from 'react';
import loginImage from '../assets/imgs/parking/parking.jpg'
import {login, signup} from "../redux/actions/authActions";
import {useAppDispatch} from "../redux/store/store";

const LoginPage = () => {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmedPassword, setPasswordC] = useState('');
    const [error, setError] = useState('');
    const [signUp, setSignUp] = useState(false);

    const dispatch = useAppDispatch();

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        dispatch(login(email, password));
    };

    const createAccount = async (e: React.FormEvent) => {
        e.preventDefault();
        dispatch(signup(email, password, confirmedPassword));
    };

    const showSignUpPage = () => {
        setSignUp(true);
    };

    const showLoginPage = () => {
        setSignUp(false);
    };

    return (
        <div className="flex justify-center h-screen">
            <div className="hidden bg-cover lg:block lg:w-2/3" style={{ backgroundImage: `url(${loginImage})` }}>
            </div>
            <div className="flex items-center w-full max-w-md px-6 mx-auto lg:w-2/6">
                <div className="flex-1">
                    {!signUp &&
                        (<form onSubmit={handleSubmit}>
                            {error && <div className="mb-4 text-red-500">{error}</div>}
                            <div className="mb-4">
                                <label htmlFor="email"
                                       className="block mb-2 text-sm text-gray-600 dark:text-gray-200">Email</label>
                                <input
                                    type="email"
                                    id="email"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                    placeholder="Email"
                                    className="block w-full px-4 py-2 mt-2 text-gray-700 placeholder-gray-400 bg-white border border-gray-200 rounded-lg dark:placeholder-gray-600 dark:bg-gray-900 dark:text-gray-300 dark:border-gray-700 focus:border-blue-400 dark:focus:border-blue-400 focus:ring-blue-400 focus:outline-none focus:ring focus:ring-opacity-40"
                                    required/>
                            </div>
                            <div className="mb-6">
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
                            <div className="mt-6">
                                <button type="submit"
                                        className="w-full px-4 py-2 tracking-wide text-white transition-colors duration-300 transform bg-blue-700 rounded-lg hover:bg-blue-900 focus:outline-none focus:bg-green-400 focus:ring focus:ring-green-300 focus:ring-opacity-50">
                                    Sign In
                                </button>
                            </div>
                        </form>)
                    }

                    {signUp &&
                        (<form onSubmit={createAccount}>
                            {error && <div className="mb-4 text-red-500">{error}</div>}
                            <div className="mb-4">
                                <label htmlFor="email"
                                       className="block mb-2 text-sm text-gray-600 dark:text-gray-200">Email</label>
                                <input
                                    type="email"
                                    id="email"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                    placeholder="Email"
                                    className="block w-full px-4 py-2 mt-2 text-gray-700 placeholder-gray-400 bg-white border border-gray-200 rounded-lg dark:placeholder-gray-600 dark:bg-gray-900 dark:text-gray-300 dark:border-gray-700 focus:border-blue-400 dark:focus:border-blue-400 focus:ring-blue-400 focus:outline-none focus:ring focus:ring-opacity-40"
                                    required/>
                            </div>
                            <div className="mb-6">
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
                            <div className="mb-6">
                                <label htmlFor="passwordc"
                                       className="block mb-2 text-sm text-gray-600 dark:text-gray-200">Confirm
                                    Password</label>
                                <input
                                    type="password"
                                    id="passwordc"
                                    value={confirmedPassword}
                                    onChange={(e) => setPasswordC(e.target.value)}
                                    placeholder="Confirm Password"
                                    className="block w-full px-4 py-2 mt-2 text-gray-700 placeholder-gray-400 bg-white border border-gray-200 rounded-lg dark:placeholder-gray-600 dark:bg-gray-900 dark:text-gray-300 dark:border-gray-700 focus:border-blue-400 dark:focus:border-blue-400 focus:ring-blue-400 focus:outline-none focus:ring focus:ring-opacity-40"
                                    required
                                />
                            </div>
                            <div className="mt-6">
                                <button type="submit"
                                        className="w-full px-4 py-2 tracking-wide text-white transition-colors duration-300 transform bg-blue-700 rounded-lg hover:bg-blue-900 focus:outline-none focus:bg-green-400 focus:ring focus:ring-green-300 focus:ring-opacity-50">
                                    Sign Up
                                </button>
                            </div>
                        </form>)
                    }
                    {
                        signUp &&
                        (
                        <div className="mt-4 text-center">
                            <p className="text-sm text-gray-600 dark:text-gray-400">
                            Do you have an account?{' '}
                                <button onClick={showLoginPage}>Log In</button>
                            </p>
                        </div>
                        )
                    }

                    {
                        !signUp &&
                        (
                            <div className="mt-4 text-center">
                                <p className="text-sm text-gray-600 dark:text-gray-400">
                                    Create an account?{' '}
                                    <button onClick={showSignUpPage}>Sign Up</button>
                                </p>
                            </div>
                        )
                    }

                </div>
            </div>
        </div>
    );
};


export default LoginPage;


