const PROXY_CONFIG = [
  {
    context: ['/api'],
    target: process.env.apiUrl || 'https://service.pallav.ind.in',
    secure: process.env.apiUrl
      ? process.env.apiUrl.startsWith('https')
        ? true
        : false
      : true,
    pathRewrite: {
      '^/api': '',
    },
    changeOrigin: true,
    debugger: true,
  },
];

module.exports = PROXY_CONFIG;
