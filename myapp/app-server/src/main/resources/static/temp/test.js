const domContainer = document.querySelector('#like_button_container');
const root = ReactDOM.createRoot(domContainer);


class LikeButton extends React.Component{

  constructor(props){
    super(props);
    this.state = {
      liked: false,

    };
  }
render(){
  if(this.state.liked){
    return "좋아합니다";
  }
  
 return React.CreateElement(
  "button",
  {
    onClick: ()=> {
      this.setState({liked : true});
    },
  },
  "좋아요"
 );
}
}

root.render(React.CreateElement(LikeButton));
