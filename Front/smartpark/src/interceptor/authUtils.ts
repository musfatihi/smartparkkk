export const getTokenFromLocalStorage = () => {
  return localStorage.getItem('token');
};

export const getInitialAuthState = () => {
  const token = getTokenFromLocalStorage();
  return {
    auth: {
      token: token ?? null,
      error: null,
    },
  };
};