/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,ts}", "./node_modules/flowbite/**/*.js"],
  theme: {
    extend: {
      colors: {
        "red": "#FF0000",
        "black": "#000000",
        "dark-gray": "#333333",
        "silver": "#C0C0C0",
        "navy-blue": "#003366",
        "white": "#FFFFFF",
        "orange": "#FFA500",
        "btn_focus": "#e5e7eb"
      },
    },
  },
  plugins: [require("flowbite/plugin")],
};
