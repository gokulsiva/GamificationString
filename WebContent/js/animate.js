function animate({timing, draw, duration}) {
	
	var r = $.Deferred();

  let start = performance.now();

  requestAnimationFrame(function animate(time) {
    // timeFraction goes from 0 to 1
    let timeFraction = (time - start) / duration;
    if (timeFraction > 1) timeFraction = 1;

    // calculate the current animation state
    let progress = timing(timeFraction);

    draw(progress); // draw it

    if (timeFraction < 1) {
      requestAnimationFrame(animate);
      setTimeout(r.resolve, 6000);
    }
    

  });
  
  return r.promise();
}